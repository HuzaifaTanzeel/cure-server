package com.example.demo.Service.MedicalHistory.ServiceImpl;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.DTO.MedicalHistory.ClinicalProcedureDTO;
import com.example.demo.DTO.MedicalHistory.PatientSurgicalHistoryDTO;
import com.example.demo.DTOMapper.MedicalHistory.PatientSurgicalHistoryMapper;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.MedicalHistory.ClinicalProcedure;
import com.example.demo.Model.MedicalHistory.PatientSurgicalHistory;
import com.example.demo.Repository.AppointmentManagement.EncounterRepository;
import com.example.demo.Repository.MedicalHistory.ClinicalProcedureRepository;
import com.example.demo.Repository.MedicalHistory.PatientSurgHistRepository;
import com.example.demo.Service.MedicalHistory.SurgicalHistoryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurgicalHistoryServiceImpl implements SurgicalHistoryService {



    private final AdmPatientRepository admPatientRepository;

    private final EncounterRepository encounterRepository;

    private final ModelMapper modelMapper;


    private final PatientSurgicalHistoryMapper surgicalHistoryMapper;

    private final ClinicalProcedureRepository clinicalProcedureRepository;


    private final PatientSurgHistRepository patientSurgHistRepository;

    @Autowired
    public SurgicalHistoryServiceImpl(AdmPatientRepository admPatientRepository, EncounterRepository encounterRepository, ModelMapper modelMapper, PatientSurgicalHistoryMapper surgicalHistoryMapper, ClinicalProcedureRepository clinicalProcedureRepository, PatientSurgHistRepository patientSurgHistRepository) {
        this.admPatientRepository = admPatientRepository;
        this.encounterRepository = encounterRepository;
        this.modelMapper = modelMapper;
        this.surgicalHistoryMapper = surgicalHistoryMapper;
        this.clinicalProcedureRepository = clinicalProcedureRepository;
        this.patientSurgHistRepository = patientSurgHistRepository;
    }

    @Transactional
    @Override
    public List<ClinicalProcedureDTO> getAllProcedures(){
        List<ClinicalProcedure> procedures = clinicalProcedureRepository.findAll();
        return procedures.stream()
                .map(procedure -> modelMapper.map(procedure, ClinicalProcedureDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ClinicalProcedureDTO> getProceduresByKeyword(String keyWord){
        List<ClinicalProcedure> procedures = clinicalProcedureRepository.findProcedureByKeyword(keyWord);
        return procedures.stream()
                .map(procedure -> modelMapper.map(procedure, ClinicalProcedureDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PatientSurgicalHistoryDTO addPatientSurgicalHistory(PatientSurgicalHistoryDTO surgicalHistoryDTO) {
        // Map DTO to entity
        PatientSurgicalHistory surgicalHistory = surgicalHistoryMapper.dtoToEntity(surgicalHistoryDTO);

        // Associate with patient
        AdmPatient admPatient = admPatientRepository.findPatientByPartyIdAndRoleTypeId(
                surgicalHistoryDTO.getPatientId(),
                surgicalHistoryDTO.getPartyRoleTypeId()
        );

        Optional<ClinicalProcedure> clinicalProcedure = clinicalProcedureRepository.findById(surgicalHistoryDTO.getClinicalProcedure().getProcedureId());


        if (surgicalHistoryDTO.getEncounterId() != null) {
            Optional<Encounter> encounter = encounterRepository.findById(surgicalHistoryDTO.getEncounterId());
            if (encounter.isPresent()) {
                Encounter encounterRecord = encounter.get();
//              patientAllergy.setEncounter(encounterRecord);
                encounterRecord.addPatientSurgicalHistory(surgicalHistory);
                encounterRepository.save(encounterRecord);
            } else {
                throw new RuntimeException("Encounter with ID " + surgicalHistoryDTO.getEncounterId() + " not found");
            }
        }

        admPatient.addPatientSurgicalHistory(surgicalHistory);
        admPatientRepository.save(admPatient);


        // Save the surgical history
        surgicalHistory = patientSurgHistRepository.save(surgicalHistory);

        // Map entity back to DTO and return
        return surgicalHistoryMapper.entityToDto(surgicalHistory);
    }

    @Transactional
    @Override
    public List<PatientSurgicalHistoryDTO> getPatientSurgicalHistories(Long patientId) {
        List<PatientSurgicalHistory> surgicalHistories = patientSurgHistRepository.findByPatientId(patientId);
        return surgicalHistories.stream()
                .map(surgicalHistoryMapper::entityToDto)
                .collect(Collectors.toList());
    }


}
