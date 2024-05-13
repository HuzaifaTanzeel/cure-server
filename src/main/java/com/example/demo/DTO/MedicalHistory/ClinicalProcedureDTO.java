package com.example.demo.DTO.MedicalHistory;

import com.example.demo.CommonFramework.Enumerators.ProcedureCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalProcedureDTO {

    private Long procedureId;
    private String code;
    private String name;
    private ProcedureCategory category;
    private String description;
}