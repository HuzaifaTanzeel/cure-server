package com.example.demo.DTO.MedicalHistory;

import com.example.demo.CommonFramework.Enumerators.AllergyCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllergenDTO {
    private Long allergenId;
    private String allergyName;
    private AllergyCategory allergyCategoryCd;
    private String description;

    // Constructors, getters, and setters
}

