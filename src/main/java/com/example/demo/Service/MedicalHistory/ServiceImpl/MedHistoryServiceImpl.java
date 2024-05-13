package com.example.demo.Service.MedicalHistory.ServiceImpl;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.DTO.MedicalHistory.AllergenDTO;
import com.example.demo.DTO.MedicalHistory.ClnConditionDTO;
import com.example.demo.DTO.MedicalHistory.ClnPatientConditionDTO;
import com.example.demo.DTOMapper.MedicalHistory.PatientAllergyMapper;
import com.example.demo.DTOMapper.MedicalHistory.PatientConditionMapper;
import com.example.demo.DTOMapper.MedicalHistory.PatientSurgicalHistoryMapper;
import com.example.demo.Model.Allergy.Allergen;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.MedicalCondition.ClnCondition;
import com.example.demo.Model.MedicalCondition.ClnPatientProblem;
import com.example.demo.Repository.AppointmentManagement.EncounterRepository;
import com.example.demo.Service.MedicalHistory.MedHistoryService;
import com.example.demo.Repository.MedicalHistory.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedHistoryServiceImpl implements MedHistoryService {

    @Autowired
    private final AdmPatientRepository admPatientRepository;

    @Autowired
    private final ClnConditionRepository clnConditionRepository;

    @Autowired
    private final PatientConditionRepository patientConditionRepository;

    @Autowired
    private final PatientConditionMapper patientConditionMapper;

    @Autowired
    private final EncounterRepository encounterRepository;

    @Autowired
    private final ModelMapper modelMapper;



    public MedHistoryServiceImpl(PatientAllergyRepository patientAllergyRepository, AdmPatientRepository admPatientRepository, AllergenRepository allergenRepository, ClnConditionRepository clnConditionRepository, PatientConditionRepository patientConditionRepository, PatientConditionMapper patientConditionMapper, EncounterRepository encounterRepository, PatientAllergyMapper patientAllergyMapper, ModelMapper modelMapper, PatientAllergyReactionRepository patientAllergyReactionRepository, PatientSurgicalHistoryMapper surgicalHistoryMapper, ClinicalProcedureRepository clinicalProcedureRepository, PatientSurgHistRepository patientSurgHistRepository) {
        this.admPatientRepository = admPatientRepository;
        this.clnConditionRepository = clnConditionRepository;
        this.patientConditionRepository = patientConditionRepository;
        this.patientConditionMapper = patientConditionMapper;
        this.encounterRepository = encounterRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public ClnPatientConditionDTO addPatientCondition(ClnPatientConditionDTO clnPatientConditionDTO){
        ClnPatientProblem cLnPatientProblem = patientConditionMapper.dtoToEntity(clnPatientConditionDTO);

        AdmPatient admPatient = admPatientRepository.findPatientByPartyIdAndRoleTypeId(
                clnPatientConditionDTO.getPatientId(),
                clnPatientConditionDTO.getPartyRoleTypeId()
        );
        Optional<ClnCondition> condition = clnConditionRepository.findById(clnPatientConditionDTO.getClnCondition().getConditionId());

        // Check if encounterId is not null
        if (clnPatientConditionDTO.getEncounterId() != null) {
            Optional<Encounter> encounter = encounterRepository.findById(clnPatientConditionDTO.getEncounterId());
            if (encounter.isPresent()) {
                Encounter encounterRecord = encounter.get();
                //clnPatientCondition.setEncounter(encounterRecord);
                encounterRecord.addPatientConditions(cLnPatientProblem);
                encounterRepository.save(encounterRecord);
            } else {
                throw new RuntimeException("Encounter with ID " + clnPatientConditionDTO.getEncounterId() + " not found");
            }
        }

        if (condition.isPresent()) {
            ClnCondition clnCondition = condition.get();
//          clnPatientCondition.setClnCondition(clnCondition);
            clnCondition.addPatientConditions(cLnPatientProblem);
            clnConditionRepository.save(clnCondition);
        }


        admPatient.addPatientConditions(cLnPatientProblem);
//        clnPatientCondition.setAdmPatient(admPatient);
        admPatientRepository.save(admPatient);

        patientConditionRepository.save(cLnPatientProblem);
        return patientConditionMapper.entityToDto(cLnPatientProblem);
    }

    @Transactional
    @Override
    public List<ClnPatientConditionDTO> getPatientMedicalConditions(Long partyId) {
        List<ClnPatientProblem> patientProblems = patientConditionRepository.findByPatientId(partyId);
        List<ClnPatientConditionDTO> clnPatientConditionDTOS = new ArrayList<>();

        for (ClnPatientProblem clnPatientProblem : patientProblems) {
            clnPatientConditionDTOS.add(patientConditionMapper.entityToDto(clnPatientProblem));
        }

        return clnPatientConditionDTOS;
    }

    @Transactional
    @Override
    public void deletePatientMedicalCondition(Long id) {
        // Find the social history record to delete
        ClnPatientProblem existingRecord = patientConditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical History Record not found"));

        // Delete the record
        patientConditionRepository.delete(existingRecord);
    }


    @Override
    public List<ClnConditionDTO> getAllConditions() {
        List<ClnCondition> conditions = clnConditionRepository.findAll();
        return conditions.stream()
                .map(condition -> modelMapper.map(condition, ClnConditionDTO.class))
                .collect(Collectors.toList());
    }



    @Transactional
    @Override
    public List<ClnConditionDTO> getConditionsByKeyword(String keyWord){
        List<ClnCondition> conditions = clnConditionRepository.findConditionsByKeyword(keyWord);
        return conditions.stream()
                .map(condition -> modelMapper.map(condition, ClnConditionDTO.class))
                .collect(Collectors.toList());
    }


}
