package com.example.demo.Service.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.ClnConditionDTO;
import com.example.demo.DTO.MedicalHistory.ClnPatientConditionDTO;

import java.util.List;

public interface MedHistoryService {

    public List<ClnConditionDTO> getAllConditions();

    public ClnPatientConditionDTO addPatientCondition(ClnPatientConditionDTO clnPatientConditionDTO);

    public List<ClnPatientConditionDTO> getPatientMedicalConditions(Long partyId);

    public void deletePatientMedicalCondition(Long id);

    public List<ClnConditionDTO> getConditionsByKeyword(String keyWord);




}
