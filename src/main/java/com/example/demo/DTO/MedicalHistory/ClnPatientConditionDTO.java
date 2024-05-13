package com.example.demo.DTO.MedicalHistory;

import com.example.demo.CommonFramework.Enumerators.ClinicalStatus;
import com.example.demo.CommonFramework.Enumerators.CondSeverity;
import com.example.demo.CommonFramework.Enumerators.CondVerfStatus;
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
public class ClnPatientConditionDTO {

    private Long problemId;
    private Long patientId;
    private Long partyRoleTypeId;
//    private Long conditionId;
    private Long encounterId;
    private ClinicalStatus clinicalStatusCd;
    private CondVerfStatus verificationStatusCd;
    private CondSeverity severityCd;
    private LocalDate recordedDateDt;
    private LocalDateTime onsetDtTm;
    private LocalDate resolvedDt;
    private String chronicInd;
    private String comment;
    private ClnConditionDTO clnCondition;
}
