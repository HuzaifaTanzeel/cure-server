package com.example.demo.Service.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.PatientSocialHistoryDTO;

import java.util.List;

public interface SocialHistoryService {

    public PatientSocialHistoryDTO addPatientSocialHistory(PatientSocialHistoryDTO patientSocialHistoryDTO);

    public PatientSocialHistoryDTO updatePatientSocialHistory(Long id, PatientSocialHistoryDTO patientSocialHistoryDTO);

    public void deletePatientSocialHistory(Long id);

    public List<PatientSocialHistoryDTO> getPatientSocialHistories(Long partyId);
}
