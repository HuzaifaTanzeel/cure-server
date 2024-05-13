package com.example.demo.Service.MedicalHistory.ServiceImpl;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.DTO.MedicalHistory.PatientSocialHistoryDTO;
import com.example.demo.DTOMapper.MedicalHistory.PatientSocialHistoryMapper;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.MedicalHistory.PatientSocialHistory;
import com.example.demo.Repository.AppointmentManagement.EncounterRepository;
import com.example.demo.Repository.MedicalHistory.PatientSocialHistoryRepo;
import com.example.demo.Service.MedicalHistory.SocialHistoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SocialHistoryServiceImpl implements SocialHistoryService {

    private final PatientSocialHistoryMapper patientSocialHistoryMapper;

    private final PatientSocialHistoryRepo patientSocialHistoryRepo;

    private final AdmPatientRepository admPatientRepository;

    private final EncounterRepository encounterRepository;

    @Autowired
    public SocialHistoryServiceImpl(PatientSocialHistoryMapper patientSocialHistoryMapper, PatientSocialHistoryRepo patientSocialHistoryRepo, AdmPatientRepository admPatientRepository, EncounterRepository encounterRepository) {
        this.patientSocialHistoryMapper = patientSocialHistoryMapper;
        this.patientSocialHistoryRepo = patientSocialHistoryRepo;
        this.admPatientRepository = admPatientRepository;
        this.encounterRepository = encounterRepository;
    }

    @Override
    public PatientSocialHistoryDTO addPatientSocialHistory(PatientSocialHistoryDTO patientSocialHistoryDTO) {


        PatientSocialHistory patientSocialHistory = patientSocialHistoryMapper.dtoToEntity(patientSocialHistoryDTO);

        AdmPatient admPatient = admPatientRepository.findPatientByPartyIdAndRoleTypeId(
                patientSocialHistoryDTO.getPatientId(),
                patientSocialHistoryDTO.getPartyRoleTypeId()
        );

        // Check if encounterId is not null
        if (patientSocialHistoryDTO.getEncounterId() != null) {
            Optional<Encounter> encounter = encounterRepository.findById(patientSocialHistoryDTO.getEncounterId());
            if (encounter.isPresent()) {
                Encounter encounterRecord = encounter.get();
                encounterRecord.addPatientSocialHistory(patientSocialHistory);
                encounterRepository.save(encounterRecord);
            } else {
                throw new RuntimeException("Encounter with ID " + patientSocialHistoryDTO.getEncounterId() + " not found");
            }
        }



        admPatient.addPatientSocialHistory(patientSocialHistory);
        admPatientRepository.save(admPatient);


        patientSocialHistoryRepo.save(patientSocialHistory);
        return patientSocialHistoryMapper.entityToDto(patientSocialHistory);
    }


    @Transactional
    @Override
    public PatientSocialHistoryDTO updatePatientSocialHistory(Long id, PatientSocialHistoryDTO patientSocialHistoryDTO) {
        // Find the existing social history record
        PatientSocialHistory existingRecord = patientSocialHistoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Social history record not found"));

        // Get the current system date
        LocalDate currentDate = LocalDate.now();

        // Compare the recorded date with the current system date
        Instant instant = existingRecord.getRecordedDate().toInstant();
        LocalDate recordedLocalDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());

        if (!recordedLocalDate.equals(currentDate)) {
            throw new RuntimeException("Cannot update social history on a different date than recorded");
        }

        // Map updated fields from DTO to entity
        existingRecord=patientSocialHistoryMapper.dtoToEntity(patientSocialHistoryDTO, existingRecord);

        // Save the updated record
        PatientSocialHistory updatedRecord = patientSocialHistoryRepo.save(existingRecord);

        // Map entity to DTO and return
        return patientSocialHistoryMapper.entityToDto(updatedRecord);
    }

    @Transactional
    @Override
    public void deletePatientSocialHistory(Long id) {
        // Find the social history record to delete
        PatientSocialHistory existingRecord = patientSocialHistoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Social history record not found"));

        // Delete the record
        patientSocialHistoryRepo.delete(existingRecord);
    }

    @Transactional
    @Override
    public List<PatientSocialHistoryDTO> getPatientSocialHistories(Long partyId) {
        List<PatientSocialHistory> patientSocialHistories = patientSocialHistoryRepo.findByPatientId(partyId);
        List<PatientSocialHistoryDTO> patientSocialHistoryDTOS = new ArrayList<>();

        for (PatientSocialHistory patientSocialHistory : patientSocialHistories) {
            patientSocialHistoryDTOS.add(patientSocialHistoryMapper.entityToDto(patientSocialHistory));
        }

        return patientSocialHistoryDTOS;
    }
}
