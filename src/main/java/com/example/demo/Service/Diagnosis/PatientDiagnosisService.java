package com.example.demo.Service.Diagnosis;

import com.example.demo.DTO.Diagnosis.ClnPatientDiagnosisDTO;
import com.example.demo.DTO.Diagnosis.ClnServiceRequestDTO;

import java.util.List;

public interface PatientDiagnosisService {

    public ClnPatientDiagnosisDTO addPatientDiagnosis(ClnPatientDiagnosisDTO clnPatientDiagnosisDTO);

    public List<ClnPatientDiagnosisDTO> getAllPatientDiagnosisByEncounter(Long EncounterId);

    public ClnServiceRequestDTO addServiceRequest(ClnServiceRequestDTO clnServiceRequestDTO);

    public List<ClnServiceRequestDTO> getServiceRequestsByEncounter(Long EncounterId);
    public ClnServiceRequestDTO getServiceRequestsById(Long serviceRequestId);

}
