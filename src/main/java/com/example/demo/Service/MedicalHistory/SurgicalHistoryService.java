package com.example.demo.Service.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.ClinicalProcedureDTO;
import com.example.demo.DTO.MedicalHistory.PatientSurgicalHistoryDTO;

import java.util.List;

public interface SurgicalHistoryService {

    public PatientSurgicalHistoryDTO addPatientSurgicalHistory(PatientSurgicalHistoryDTO surgicalHistoryDTO);

    public List<PatientSurgicalHistoryDTO> getPatientSurgicalHistories(Long patientId);

    public List<ClinicalProcedureDTO> getAllProcedures();

    public List<ClinicalProcedureDTO> getProceduresByKeyword(String keyWord);
}
