package com.example.demo.Service.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.PatientAllergyDTO;
import com.example.demo.DTO.MedicalHistory.PatientAllergyReactionDTO;

import java.util.List;

public interface AllergyHistoryService {

    public PatientAllergyDTO addPatientAllergy(PatientAllergyDTO patientAllergyDTO);

    public List<PatientAllergyDTO> getPatientAllergies(Long partyId);

    public List<PatientAllergyReactionDTO> getPatientAllergyReactions(Long patientAllergyId);

    public void deleteAllergyHistory(Long patientAllergyId);
}
