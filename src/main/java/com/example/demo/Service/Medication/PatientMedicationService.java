package com.example.demo.Service.Medication;

import com.example.demo.DTO.Medication.MedicationRequestDTO;

import java.util.List;

public interface PatientMedicationService {

    public MedicationRequestDTO addMedicationRequest(MedicationRequestDTO medicationRequestDTO);

    public List<MedicationRequestDTO> getMedicationRequestsByEncounter(Long EncounterId);
    public MedicationRequestDTO getMedicationRequestsById(Long medRequestId);
}
