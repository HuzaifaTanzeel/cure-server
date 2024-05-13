package com.example.demo.Service.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.FamilyMemberHistoryDTO;
import com.example.demo.Enumerators.MedicalHistory.FamilyMemberRelationship;

import java.util.List;

public interface FamilyHistoryService {

    public FamilyMemberHistoryDTO addFamilyHistory(FamilyMemberHistoryDTO familyMemberHistoryDTO);

    public List<FamilyMemberHistoryDTO> getFamilyHistoryByPatientId(Long patientId);

    public List<FamilyMemberHistoryDTO> getFamilyHistoryByPatientRelationship(Long patientId, FamilyMemberRelationship relationship);

    public void deletePatientFamilyHistory(Long id);

    public FamilyMemberHistoryDTO updateFamilyHistory(Long id,FamilyMemberHistoryDTO updatedFamilyMemberHistoryDTO);
}
