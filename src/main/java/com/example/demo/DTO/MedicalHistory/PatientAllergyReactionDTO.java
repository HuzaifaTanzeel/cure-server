package com.example.demo.DTO.MedicalHistory;

import com.example.demo.CommonFramework.Enumerators.CondSeverity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientAllergyReactionDTO {

    private Long allergyReactionId;
    private Long patientAllergyId;
    private String reaction;
    private CondSeverity severityCd;

    // Constructors, getters, and setters
}