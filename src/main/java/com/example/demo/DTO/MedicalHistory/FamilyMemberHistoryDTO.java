package com.example.demo.DTO.MedicalHistory;

import com.example.demo.CommonFramework.Enumerators.GenderType;
import com.example.demo.Enumerators.MedicalHistory.FamilyHistoryStatus;
import com.example.demo.Enumerators.MedicalHistory.FamilyMemberRelationship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberHistoryDTO {
    private Long familyMemberHxId;
    private Long patientId;
    private Long partyRoleTypeId;
    private Long encounterId;
    private String fullName;
    private GenderType genderCd;
    private FamilyMemberRelationship relationshipCd;
    private LocalDate dobDt;
    private Integer age;
    private Boolean deceasedInd;
    private LocalDate deceasedDateDt;
    private LocalDate recordedDateDt;
    private FamilyHistoryStatus statusCd;
    private String comment;
    private List<ClnConditionDTO> clnConditions=new ArrayList<>();
}
