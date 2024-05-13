package com.example.demo.Service.MedicalHistory.ServiceImpl;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.DTO.MedicalHistory.ClnConditionDTO;
import com.example.demo.DTO.MedicalHistory.FamilyMemberHistoryDTO;
import com.example.demo.DTOMapper.MedicalHistory.ClnConditionMapper;
import com.example.demo.DTOMapper.MedicalHistory.FamilyHistoryMapper;
import com.example.demo.Enumerators.MedicalHistory.FamilyMemberRelationship;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.MedicalCondition.ClnCondition;
import com.example.demo.Model.MedicalHistory.FamilyMemberHistory;
import com.example.demo.Model.MedicalHistory.FamilyMemberHistoryCondition;
import com.example.demo.Repository.AppointmentManagement.EncounterRepository;
import com.example.demo.Repository.MedicalHistory.ClnConditionRepository;
import com.example.demo.Repository.MedicalHistory.FamilyMemberHistoryConditionRepository;
import com.example.demo.Repository.MedicalHistory.FamilyMemberHistoryRepository;
import com.example.demo.Service.MedicalHistory.FamilyHistoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FamilyHistoryServiceImpl implements FamilyHistoryService {


    private final FamilyMemberHistoryRepository familyMemberHistoryRepository;

    private final ClnConditionRepository clnConditionRepository;
    private final FamilyHistoryMapper familyHistoryMapper;

    private final ClnConditionMapper clnConditionMapper;

    private final AdmPatientRepository admPatientRepository;

    private final EncounterRepository encounterRepository;

    private final FamilyMemberHistoryConditionRepository familyMemberHistoryConditionRepository;

    @Autowired
    public FamilyHistoryServiceImpl(FamilyMemberHistoryRepository familyMemberHistoryRepository, ClnConditionRepository clnConditionRepository, FamilyHistoryMapper familyHistoryMapper, ClnConditionMapper clnConditionMapper, AdmPatientRepository admPatientRepository, EncounterRepository encounterRepository, FamilyMemberHistoryConditionRepository familyMemberHistoryConditionRepository) {
        this.familyMemberHistoryRepository = familyMemberHistoryRepository;
        this.clnConditionRepository = clnConditionRepository;
        this.familyHistoryMapper = familyHistoryMapper;
        this.clnConditionMapper = clnConditionMapper;
        this.admPatientRepository = admPatientRepository;
        this.encounterRepository = encounterRepository;
        this.familyMemberHistoryConditionRepository = familyMemberHistoryConditionRepository;
    }

    @Transactional
    public void validateFamilyMemberHistoryDTO(FamilyMemberHistoryDTO familyMemberHistoryDTO){

        if (familyMemberHistoryRepository.existsByPatientIdAndRelationshipCd(
                familyMemberHistoryDTO.getPatientId(), familyMemberHistoryDTO.getRelationshipCd())) {
            throw new RuntimeException("The family member for this patient already exists."+ "Please update the existing record or add a new one by deleting the current entry.");
        }
    }


    @Transactional
    @Override
    public FamilyMemberHistoryDTO addFamilyHistory(FamilyMemberHistoryDTO familyMemberHistoryDTO) {

        validateFamilyMemberHistoryDTO(familyMemberHistoryDTO);
        // Map DTO to entity
        FamilyMemberHistory familyMemberHistory = familyHistoryMapper.dtoToEntity(familyMemberHistoryDTO);

        // Get condition IDs from DTO
        List<Long> conditionIds = familyMemberHistoryDTO.getClnConditions().stream()
                .map(ClnConditionDTO::getConditionId)
                .collect(Collectors.toList());

        // Fetch conditions from repository
        List<ClnCondition> conditions = clnConditionRepository.findAllById(conditionIds);

        // Save family member history entity
        familyMemberHistory = familyMemberHistoryRepository.save(familyMemberHistory);

        // Create and save family member history conditions
        for (ClnCondition condition : conditions) {
            FamilyMemberHistoryCondition familyMemberHistoryCondition = new FamilyMemberHistoryCondition(familyMemberHistory, condition);
            familyMemberHistoryConditionRepository.save(familyMemberHistoryCondition);
            condition.addFamilyMemberHistoryConditions(familyMemberHistoryCondition);
            familyMemberHistory.addFamilyMemberHistoryConditions(familyMemberHistoryCondition);
            clnConditionRepository.save(condition);
        }

        // Fetch patient from repository
        AdmPatient admPatient = admPatientRepository.findPatientByPartyIdAndRoleTypeId(
                familyMemberHistoryDTO.getPatientId(),
                familyMemberHistoryDTO.getPartyRoleTypeId()
        );

        // Associate encounter if encounterId is provided
        if (familyMemberHistoryDTO.getEncounterId() != null) {
            Optional<Encounter> optionalEncounter = encounterRepository.findById(familyMemberHistoryDTO.getEncounterId());
            if (optionalEncounter.isPresent()) {
                Encounter encounterRecord = optionalEncounter.get();
                encounterRecord.addFamilyMemberHistories(familyMemberHistory);
                encounterRepository.save(encounterRecord);
            } else {
                throw new RuntimeException("Encounter with ID " + familyMemberHistoryDTO.getEncounterId() + " not found");
            }
        }

        // Associate family member history with patient
        admPatient.addFamilyMemberHistories(familyMemberHistory);
        admPatientRepository.save(admPatient);

        // Save family member history again to update associations
        FamilyMemberHistory savedFamilyHistory = familyMemberHistoryRepository.save(familyMemberHistory);

        // Map saved entity to DTO
        FamilyMemberHistoryDTO savedFamilyHistoryDTO = familyHistoryMapper.entityToDto(savedFamilyHistory);

        // Fetch associated conditions
        List<ClnCondition> associatedConditions = savedFamilyHistory.getFamilyMemberHistoryConditionList().stream()
                .map(FamilyMemberHistoryCondition::getClnCondition)
                .toList();

        // Map associated conditions to DTOs
        List<ClnConditionDTO> conditionDTOs = associatedConditions.stream()
                .map(clnConditionMapper::entityToDto)
                .collect(Collectors.toList());

        // Populate the clnConditions list in the DTO
        savedFamilyHistoryDTO.setClnConditions(conditionDTOs);

        return savedFamilyHistoryDTO;
    }


    @Transactional
    @Override
    public List<FamilyMemberHistoryDTO> getFamilyHistoryByPatientId(Long patientId) {

        List<FamilyMemberHistory> familyHistoryList = familyMemberHistoryRepository.findByPatientId(patientId);
        Long temp=familyHistoryList.get(0).getFamilyMemberHxId();
        return familyHistoryList.stream()
                .map(familyHistory -> {
                    FamilyMemberHistoryDTO familyHistoryDTO = familyHistoryMapper.entityToDto(familyHistory);
                    // Set associated conditions in the DTO
                    List<ClnConditionDTO> conditionDTOs = familyHistory.getFamilyMemberHistoryConditionList().stream()
                            .map(FamilyMemberHistoryCondition::getClnCondition)
                            .map(clnConditionMapper::entityToDto)
                            .collect(Collectors.toList());
                    familyHistoryDTO.setClnConditions(conditionDTOs);
                    return familyHistoryDTO;
                })
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public List<FamilyMemberHistoryDTO> getFamilyHistoryByPatientRelationship(Long patientId, FamilyMemberRelationship relationship) {


        List<FamilyMemberHistory> familyHistoryList = familyMemberHistoryRepository.findByPatientIdAndRelationshipCd(patientId, relationship);

        return familyHistoryList.stream()
                .map(familyHistory -> {
                    FamilyMemberHistoryDTO familyHistoryDTO = familyHistoryMapper.entityToDto(familyHistory);
                    // Set associated conditions in the DTO
                    List<ClnConditionDTO> conditionDTOs = familyHistory.getFamilyMemberHistoryConditionList().stream()
                            .map(FamilyMemberHistoryCondition::getClnCondition)
                            .map(clnConditionMapper::entityToDto)
                            .collect(Collectors.toList());
                    familyHistoryDTO.setClnConditions(conditionDTOs);
                    return familyHistoryDTO;
                })
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public FamilyMemberHistoryDTO updateFamilyHistory(Long id,FamilyMemberHistoryDTO updatedFamilyMemberHistoryDTO) {
        // Retrieve existing family member history entity from the database
        FamilyMemberHistory existingFamilyMemberHistory = familyMemberHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Family member history not found with ID: " + updatedFamilyMemberHistoryDTO.getFamilyMemberHxId()));

        // Update the fields of the retrieved entity with the new data from the DTO
        updateFamilyMemberHistoryFromDTO(existingFamilyMemberHistory, updatedFamilyMemberHistoryDTO);

        // Save the updated family member history entity back to the database
        FamilyMemberHistory savedFamilyMemberHistory = familyMemberHistoryRepository.save(existingFamilyMemberHistory);

        // Retrieve the condition IDs from the DTO
        List<Long> conditionIds = updatedFamilyMemberHistoryDTO.getClnConditions().stream()
                .map(ClnConditionDTO::getConditionId)
                .collect(Collectors.toList());

        // Fetch the conditions from the repository using the condition IDs
        List<ClnCondition> conditions = clnConditionRepository.findAllById(conditionIds);

        // Remove existing family member history conditions
        existingFamilyMemberHistory.getFamilyMemberHistoryConditionList().clear();

        // Create new family member history conditions for each condition fetched
        for (ClnCondition condition : conditions) {
            FamilyMemberHistoryCondition familyMemberHistoryCondition = new FamilyMemberHistoryCondition(existingFamilyMemberHistory, condition);
            familyMemberHistoryConditionRepository.save(familyMemberHistoryCondition);
            condition.addFamilyMemberHistoryConditions(familyMemberHistoryCondition);
            existingFamilyMemberHistory.addFamilyMemberHistoryConditions(familyMemberHistoryCondition);
            clnConditionRepository.save(condition);
        }

        // Save the updated family member history entity again to ensure that the associations are persisted
        FamilyMemberHistory savedFamilyHistory = familyMemberHistoryRepository.save(existingFamilyMemberHistory);

        // Map the updated entity to DTO
        FamilyMemberHistoryDTO savedFamilyMemberHistoryDTO = familyHistoryMapper.entityToDto(savedFamilyHistory);

        // Map associated conditions to DTOs
        List<ClnConditionDTO> conditionDTOs = savedFamilyHistory.getFamilyMemberHistoryConditionList().stream()
                .map(FamilyMemberHistoryCondition::getClnCondition)
                .map(clnConditionMapper::entityToDto)
                .collect(Collectors.toList());

        // Populate the clnConditions list in the DTO
        savedFamilyMemberHistoryDTO.setClnConditions(conditionDTOs);

        return savedFamilyMemberHistoryDTO;
    }

    // Helper method to update family member history entity from DTO
    private void updateFamilyMemberHistoryFromDTO(FamilyMemberHistory familyMemberHistory, FamilyMemberHistoryDTO dto) {
        familyMemberHistory.setFullName(dto.getFullName());
        familyMemberHistory.setGenderCd(dto.getGenderCd());
        familyMemberHistory.setRelationshipCd(dto.getRelationshipCd());
        familyMemberHistory.setDobDt(dto.getDobDt());
        familyMemberHistory.setAge(dto.getAge());
        familyMemberHistory.setDeceasedInd(dto.getDeceasedInd());
        familyMemberHistory.setDeceasedDateDt(dto.getDeceasedDateDt());
        familyMemberHistory.setRecordedDateDt(dto.getRecordedDateDt());
        familyMemberHistory.setStatusCd(dto.getStatusCd());
        familyMemberHistory.setComment(dto.getComment());
    }





    @Transactional
    @Override
    public void deletePatientFamilyHistory(Long id) {
        // Find the social history record to delete
        FamilyMemberHistory existingRecord = familyMemberHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Social history record not found"));

        // Delete the record
        familyMemberHistoryRepository.delete(existingRecord);
    }




}
