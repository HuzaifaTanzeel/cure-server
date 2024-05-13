package com.example.demo.DTO.MedicalHistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClnConditionDTO {

    private Long conditionId;
    private String conditionCode;
    private String name;
    private String definition;
    private String codeSystem;

    // If needed, add any additional fields, constructors, or methods here
}