package com.example.demo.Service.Diagnosis.ServiceImpl;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.DTO.Diagnosis.ClnPatientDiagnosisDTO;
import com.example.demo.DTO.Diagnosis.ClnServiceRequestDTO;
import com.example.demo.DTOMapper.Diagnosis.PatientDiagnosisMapper;
import com.example.demo.DTOMapper.Diagnosis.ServiceRequestMapper;
import com.example.demo.DTOMapper.MedicalHistory.PatientConditionMapper;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.Diagnosis.ClinicalServicePatientDiagnosis;
import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
import com.example.demo.Model.Diagnosis.ClnServiceRequest;
import com.example.demo.Model.MedicalCondition.ClnCondition;
import com.example.demo.Repository.Diagnosis.ClinicalServicePatientDiagnosisRepository;
import com.example.demo.Repository.Diagnosis.PatientDiagnosisRepository;
import com.example.demo.Repository.Diagnosis.ServiceRequestRepository;
import com.example.demo.Repository.AppointmentManagement.EncounterRepository;
import com.example.demo.Repository.MedicalHistory.ClnConditionRepository;
import com.example.demo.Repository.MedicalHistory.PatientConditionRepository;
import com.example.demo.Service.Diagnosis.PatientDiagnosisService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientDiagnosisServiceImpl implements PatientDiagnosisService {

    @Autowired
    private final AdmPatientRepository admPatientRepository;

    @Autowired
    private final ClnConditionRepository clnConditionRepository;

    @Autowired
    private final PatientDiagnosisMapper patientDiagnosisMapper;

    @Autowired
    private final EncounterRepository encounterRepository;

    @Autowired
    private final PatientDiagnosisRepository patientDiagnosisRepository;

    @Autowired
    private final ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private final ClinicalServicePatientDiagnosisRepository clinicalServicePatientDiagnosisRepository;

    @Autowired
    private final ServiceRequestMapper serviceRequestMapper;

    @Autowired
    private final ModelMapper modelMapper;

    public PatientDiagnosisServiceImpl(AdmPatientRepository admPatientRepository, ClnConditionRepository clnConditionRepository, PatientConditionRepository patientConditionRepository, PatientConditionMapper patientConditionMapper, PatientDiagnosisMapper patientDiagnosisMapper, EncounterRepository encounterRepository, PatientDiagnosisRepository patientDiagnosisRepository, ServiceRequestRepository serviceRequestRepository, ClinicalServicePatientDiagnosisRepository clinicalServicePatientDiagnosisRepository, ServiceRequestMapper serviceRequestMapper, ModelMapper modelMapper) {
        this.admPatientRepository = admPatientRepository;
        this.clnConditionRepository = clnConditionRepository;
        this.patientDiagnosisMapper = patientDiagnosisMapper;
        this.encounterRepository = encounterRepository;
        this.patientDiagnosisRepository = patientDiagnosisRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.clinicalServicePatientDiagnosisRepository = clinicalServicePatientDiagnosisRepository;
        this.serviceRequestMapper = serviceRequestMapper;
        this.modelMapper = modelMapper;
    }


    @Transactional
    @Override
    public ClnPatientDiagnosisDTO addPatientDiagnosis(ClnPatientDiagnosisDTO clnPatientDiagnosisDTO){
        ClnPatientDiagnosis clnPatientDiagnosis = patientDiagnosisMapper.dtoToEntity(clnPatientDiagnosisDTO);

        AdmPatient admPatient = admPatientRepository.findPatientByPartyIdAndRoleTypeId(
                clnPatientDiagnosisDTO.getPatientId(),
                clnPatientDiagnosisDTO.getPartyRoleTypeId()
        );
        Optional<ClnCondition> condition = clnConditionRepository.findById(clnPatientDiagnosisDTO.getClnCondition().getConditionId());

        // Check if encounterId is not null
        if (clnPatientDiagnosisDTO.getEncounterId() != null) {
            Optional<Encounter> encounter = encounterRepository.findById(clnPatientDiagnosisDTO.getEncounterId());
            if (encounter.isPresent()) {
                Encounter encounterRecord = encounter.get();
                //clnPatientCondition.setEncounter(encounterRecord);
                encounterRecord.addPatientDiagnosis(clnPatientDiagnosis);
                encounterRepository.save(encounterRecord);
            } else {
                throw new RuntimeException("Encounter with ID " + clnPatientDiagnosisDTO.getEncounterId() + " not found");
            }
        }

        if (condition.isPresent()) {
            ClnCondition clnCondition = condition.get();
            clnCondition.addPatientDiagnosis(clnPatientDiagnosis);
            clnConditionRepository.save(clnCondition);
        }


        admPatient.addPatientDiagnosis(clnPatientDiagnosis);
        admPatientRepository.save(admPatient);

        patientDiagnosisRepository.save(clnPatientDiagnosis);
        return patientDiagnosisMapper.entityToDto(clnPatientDiagnosis);
    }

    @Transactional
    @Override
    public List<ClnPatientDiagnosisDTO> getAllPatientDiagnosisByEncounter(Long encounterId) {
        List<ClnPatientDiagnosis> clnPatientDiagnoses = patientDiagnosisRepository.findByEncounterId(encounterId);

        // Using ModelMapper to map entity list to DTO list
        return clnPatientDiagnoses.stream()
                .map(patientDiagnosisMapper::entityToDto)
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public ClnServiceRequestDTO addServiceRequest(ClnServiceRequestDTO clnServiceRequestDTO) {


        // Map DTO to entity
        ClnServiceRequest clnServiceRequest = serviceRequestMapper.dtoToEntity(clnServiceRequestDTO);

        // Get condition IDs from DTO
        List<Long> diagnosisIds = clnServiceRequestDTO.getClnPatientDiagnosisList().stream()
                .map(ClnPatientDiagnosisDTO::getDiagnosisId)
                .collect(Collectors.toList());

        // Fetch conditions from repository
        List<ClnPatientDiagnosis> diagnoses = patientDiagnosisRepository.findAllById(diagnosisIds);

        // Save family member history entity
        clnServiceRequest = serviceRequestRepository.save(clnServiceRequest);

        // Create and save family member history conditions
        for (ClnPatientDiagnosis diagnosis : diagnoses) {
            ClinicalServicePatientDiagnosis clinicalServicePatientDiagnosis = new ClinicalServicePatientDiagnosis(clnServiceRequest, diagnosis);
            clinicalServicePatientDiagnosisRepository.save(clinicalServicePatientDiagnosis);
            diagnosis.addPatientServiceRequestDiagnosis(clinicalServicePatientDiagnosis);
            clnServiceRequest.addPatientServiceRequestDiagnosis(clinicalServicePatientDiagnosis);
            patientDiagnosisRepository.save(diagnosis);
        }

//

        // Associate encounter if encounterId is provided
        if (clnServiceRequestDTO.getEncounterId() != null) {
            Optional<Encounter> optionalEncounter = encounterRepository.findById(clnServiceRequestDTO.getEncounterId());
            if (optionalEncounter.isPresent()) {
                Encounter encounterRecord = optionalEncounter.get();
                encounterRecord.addServiceRequest(clnServiceRequest);
                encounterRepository.save(encounterRecord);
            } else {
                throw new RuntimeException("Encounter with ID " + clnServiceRequestDTO.getEncounterId() + " not found");
            }
        }



        // Save Service Request again to update associations
        ClnServiceRequest savedServiceRequest = serviceRequestRepository.save(clnServiceRequest);

        // Map saved entity to DTO
        ClnServiceRequestDTO savedServiceRequestDTO = serviceRequestMapper.entityToDto(savedServiceRequest);

        // Fetch associated diagnosis
        List<ClnPatientDiagnosis> associatedDiagnosis = savedServiceRequest.getClinicalServicePatientDiagnosisList().stream()
                .map(ClinicalServicePatientDiagnosis::getClnPatientDiagnosis)
                .toList();

        // Map associated diagnosis to DTOs
        List<ClnPatientDiagnosisDTO> diagnosisDTOs = associatedDiagnosis.stream()
                .map(patientDiagnosisMapper::entityToDto)
                .collect(Collectors.toList());

        // Populate the diagnosis list in the DTO
        savedServiceRequestDTO.setClnPatientDiagnosisList(diagnosisDTOs);

        return savedServiceRequestDTO;
    }



    @Transactional
    @Override
    public List<ClnServiceRequestDTO> getServiceRequestsByEncounter(Long encounterId) {
        List<ClnServiceRequest> clnServiceRequests = serviceRequestRepository.findByEncounterId(encounterId);

        // Mapping entity list to DTO list using ModelMapper
        List<ClnServiceRequestDTO> serviceRequestDTOs = clnServiceRequests.stream()
                .map(serviceRequest -> {
                    // Mapping service request entity to DTO
                    ClnServiceRequestDTO serviceRequestDTO = serviceRequestMapper.entityToDto(serviceRequest);

                    // Mapping associated diagnosis entities to DTOs
                    List<ClnPatientDiagnosisDTO> diagnosisDTOs = serviceRequest.getClinicalServicePatientDiagnosisList().stream()
                            .map(clinicalServicePatientDiagnosis -> patientDiagnosisMapper.entityToDto(clinicalServicePatientDiagnosis.getClnPatientDiagnosis()))
                            .collect(Collectors.toList());

                    // Setting diagnosis list in the service request DTO
                    serviceRequestDTO.setClnPatientDiagnosisList(diagnosisDTOs);

                    return serviceRequestDTO;
                })
                .collect(Collectors.toList());

        return serviceRequestDTOs;
    }


    @Transactional
    @Override
    public ClnServiceRequestDTO getServiceRequestsById(Long serviceRequestId) {
        Optional<ClnServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(serviceRequestId);

        if (optionalServiceRequest.isPresent()) {
            ClnServiceRequest serviceRequest = optionalServiceRequest.get();

            // Mapping service request entity to DTO
            ClnServiceRequestDTO serviceRequestDTO = serviceRequestMapper.entityToDto(serviceRequest);

            // Mapping associated diagnosis entities to DTOs
            List<ClnPatientDiagnosisDTO> diagnosisDTOs = serviceRequest.getClinicalServicePatientDiagnosisList().stream()
                    .map(clinicalServicePatientDiagnosis -> patientDiagnosisMapper.entityToDto(clinicalServicePatientDiagnosis.getClnPatientDiagnosis()))
                    .collect(Collectors.toList());

            // Setting diagnosis list in the service request DTO
            serviceRequestDTO.setClnPatientDiagnosisList(diagnosisDTOs);

            return serviceRequestDTO;
        }

        return null;
    }



}
