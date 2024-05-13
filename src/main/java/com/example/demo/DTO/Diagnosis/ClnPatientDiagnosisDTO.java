package com.example.demo.DTO.Diagnosis;

import com.example.demo.CommonFramework.Enumerators.ClinicalStatus;
import com.example.demo.CommonFramework.Enumerators.CondSeverity;
import com.example.demo.CommonFramework.Enumerators.CondVerfStatus;
import com.example.demo.DTO.MedicalHistory.ClnConditionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClnPatientDiagnosisDTO {
    private Long diagnosisId;
    private Long patientId;
    private Long partyRoleTypeId;
    private Long encounterId;
    private ClinicalStatus clinicalStatusCd;
    private CondVerfStatus verificationStatusCd;
    private CondSeverity severityCd;
    private LocalDate recordedDateDt;
    private LocalDateTime onsetDtTm;
    private String chronicInd;
    private String comment;
    private ClnConditionDTO clnCondition;

}
