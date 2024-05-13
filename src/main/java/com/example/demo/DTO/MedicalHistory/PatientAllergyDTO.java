package com.example.demo.DTO.MedicalHistory;

import com.example.demo.CommonFramework.Enumerators.AllergyStatus;
import com.example.demo.CommonFramework.Enumerators.AllergyType;
import com.example.demo.CommonFramework.Enumerators.ClinicalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientAllergyDTO {

    private Long patientAllergyId;

    private AllergenDTO allergen;

    private Long patientId;

    private Long partyRoleTypeId;

    private AllergyType allergyTypeCd;

    private AllergyStatus allergyStatusCd;

    private ClinicalStatus clinicalStatusCd;

    private Long encounterId;

    private LocalDateTime onsetDateTime;

    private LocalDateTime resolvedDateTime;

    private LocalDateTime lastOccurrenceDateTime;

    private String comments;

    private List<PatientAllergyReactionDTO> patientAllergyReactions=new ArrayList<>();
}
