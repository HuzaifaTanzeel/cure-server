package com.example.demo.Service.Medication.ServiceImpl;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.DTO.Diagnosis.ClnPatientDiagnosisDTO;
import com.example.demo.DTO.Medication.MedicationRequestDTO;
import com.example.demo.DTOMapper.Diagnosis.PatientDiagnosisMapper;
import com.example.demo.DTOMapper.Medication.MedicationRequestMapper;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
import com.example.demo.Model.Medication.MedRequestPatientDiagnosis;
import com.example.demo.Model.Medication.MedicationRequest;
import com.example.demo.Repository.Diagnosis.PatientDiagnosisRepository;
import com.example.demo.Repository.AppointmentManagement.EncounterRepository;
import com.example.demo.Repository.Medication.DrugRepository;
import com.example.demo.Repository.Medication.MedicationRequestPatientDiagnosisRepository;
import com.example.demo.Repository.Medication.MedicationRequestRepository;
import com.example.demo.Service.Medication.PatientMedicationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientMedicationServiceImpl implements PatientMedicationService {

    private final MedicationRequestMapper medicationRequestMapper;

    private final EncounterRepository encounterRepository;

    private final AdmPatientRepository admPatientRepository;

    private final MedicationRequestRepository medicationRequestRepository;

    private final DrugRepository drugRepository;

    private final MedicationRequestPatientDiagnosisRepository medicationRequestPatientDiagnosisRepository;

    private final PatientDiagnosisRepository patientDiagnosisRepository;

    private final PatientDiagnosisMapper patientDiagnosisMapper;

    @Autowired
    public PatientMedicationServiceImpl(MedicationRequestMapper medicationRequestMapper, EncounterRepository encounterRepository, AdmPatientRepository admPatientRepository, MedicationRequestRepository medicationRequestRepository, DrugRepository drugRepository, MedicationRequestPatientDiagnosisRepository medicationRequestPatientDiagnosisRepository, PatientDiagnosisRepository patientDiagnosisRepository, PatientDiagnosisMapper patientDiagnosisMapper) {
        this.medicationRequestMapper = medicationRequestMapper;
        this.encounterRepository = encounterRepository;
        this.admPatientRepository = admPatientRepository;
        this.medicationRequestRepository = medicationRequestRepository;
        this.drugRepository = drugRepository;
        this.medicationRequestPatientDiagnosisRepository = medicationRequestPatientDiagnosisRepository;
        this.patientDiagnosisRepository = patientDiagnosisRepository;
        this.patientDiagnosisMapper = patientDiagnosisMapper;
    }

    @Transactional
    @Override
    public MedicationRequestDTO addMedicationRequest(MedicationRequestDTO medicationRequestDTO) {


        // Map DTO to entity
        MedicationRequest medicationRequest = medicationRequestMapper.dtoToEntity(medicationRequestDTO);

        // Get condition IDs from DTO
        List<Long> diagnosisIds = medicationRequestDTO.getClnPatientDiagnosisList().stream()
                .map(ClnPatientDiagnosisDTO::getDiagnosisId)
                .collect(Collectors.toList());

        // Fetch conditions from repository
        List<ClnPatientDiagnosis> diagnoses = patientDiagnosisRepository.findAllById(diagnosisIds);

        AdmPatient admPatient = admPatientRepository.findPatientByPartyIdAndRoleTypeId(
                medicationRequestDTO.getPatientId(),
                medicationRequestDTO.getPartyRoleTypeId()
        );

        admPatient.addPatientMedicationRequest(medicationRequest);

        // Save family member history entity
        medicationRequest = medicationRequestRepository.save(medicationRequest);

        // Create and save family member history conditions
        for (ClnPatientDiagnosis diagnosis : diagnoses) {
            MedRequestPatientDiagnosis medRequestPatientDiagnosis = new MedRequestPatientDiagnosis(medicationRequest, diagnosis);
            medicationRequestPatientDiagnosisRepository.save(medRequestPatientDiagnosis);
            diagnosis.addPatientMedRequestDiagnosis(medRequestPatientDiagnosis);
            medicationRequest.addPatientMedRequestDiagnosis(medRequestPatientDiagnosis);
            patientDiagnosisRepository.save(diagnosis);
        }


//

        // Associate encounter if encounterId is provided
        if (medicationRequestDTO.getEncounterId() != null) {
            Optional<Encounter> optionalEncounter = encounterRepository.findById(medicationRequestDTO.getEncounterId());
            if (optionalEncounter.isPresent()) {
                Encounter encounterRecord = optionalEncounter.get();
                encounterRecord.addMedicationRequest(medicationRequest);
                encounterRepository.save(encounterRecord);
            } else {
                throw new RuntimeException("Encounter with ID " + medicationRequestDTO.getEncounterId() + " not found");
            }
        }


        admPatientRepository.save(admPatient);



        // Save Service Request again to update associations
        MedicationRequest savedMedicationRequest= medicationRequestRepository.save(medicationRequest);

        // Map saved entity to DTO
        MedicationRequestDTO savedMedicationRequestDTO = medicationRequestMapper.entityToDto(savedMedicationRequest);

        // Fetch associated diagnosis
        List<ClnPatientDiagnosis> associatedDiagnosis = savedMedicationRequest.getMedRequestPatientDiagnosisList().stream()
                .map(MedRequestPatientDiagnosis::getClnPatientDiagnosis)
                .toList();

        // Map associated diagnosis to DTOs
        List<ClnPatientDiagnosisDTO> diagnosisDTOs = associatedDiagnosis.stream()
                .map(patientDiagnosisMapper::entityToDto)
                .collect(Collectors.toList());

        // Populate the diagnosis list in the DTO
        savedMedicationRequestDTO.setClnPatientDiagnosisList(diagnosisDTOs);

        return savedMedicationRequestDTO;
    }

    @Transactional
    @Override
    public List<MedicationRequestDTO> getMedicationRequestsByEncounter(Long encounterId) {
        List<MedicationRequest> medicationRequests = medicationRequestRepository.findByEncounterId(encounterId);

        // Mapping entity list to DTO list using ModelMapper
        List<MedicationRequestDTO> medicationRequestDTOs = medicationRequests.stream()
                .map(medicationRequest -> {
                    // Mapping service request entity to DTO
                    MedicationRequestDTO medicationRequestDTO = medicationRequestMapper.entityToDto(medicationRequest);

                    // Mapping associated diagnosis entities to DTOs
                    List<ClnPatientDiagnosisDTO> diagnosisDTOs = medicationRequest.getMedRequestPatientDiagnosisList().stream()
                            .map(medRequestPatientDiagnosis-> patientDiagnosisMapper.entityToDto(medRequestPatientDiagnosis.getClnPatientDiagnosis()))
                            .collect(Collectors.toList());

                    // Setting diagnosis list in the service request DTO
                    medicationRequestDTO.setClnPatientDiagnosisList(diagnosisDTOs);

                    return medicationRequestDTO;
                })
                .toList();

        return medicationRequestDTOs;
    }

    @Transactional
    @Override
    public MedicationRequestDTO getMedicationRequestsById(Long medRequestId) {
        Optional<MedicationRequest> optionalMedicationRequest = medicationRequestRepository.findById(medRequestId);

        if (optionalMedicationRequest.isPresent()) {
            MedicationRequest medicationRequest = optionalMedicationRequest.get();

            // Mapping service request entity to DTO
            MedicationRequestDTO medicationRequestDTO = medicationRequestMapper.entityToDto(medicationRequest);

            // Mapping associated diagnosis entities to DTOs
            List<ClnPatientDiagnosisDTO> diagnosisDTOs = medicationRequest.getMedRequestPatientDiagnosisList().stream()
                    .map(medRequestPatientDiagnosis -> patientDiagnosisMapper.entityToDto(medRequestPatientDiagnosis.getClnPatientDiagnosis()))
                    .collect(Collectors.toList());

            // Setting diagnosis list in the service request DTO
            medicationRequestDTO.setClnPatientDiagnosisList(diagnosisDTOs);

            return medicationRequestDTO;
        }

        return null;
    }
}
