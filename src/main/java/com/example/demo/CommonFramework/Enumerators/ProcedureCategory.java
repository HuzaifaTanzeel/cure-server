package com.example.demo.CommonFramework.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum ProcedureCategory {
    SURGICAL_PROCEDURE(1201, 387713003, "SURGICAL-PROC", "Surgical procedure"),
    DIAGNOSTIC_PROCEDURE(1202, 103693007, "DIAGNOSTIC-PROC", "Diagnostic procedure");

    private final int code;
    private final int surgProcCode;
    private final String display;
    private final String description;

    ProcedureCategory(int code,int surgProcCode, String display,String description) {
        this.code = code;
        this.surgProcCode = surgProcCode;
        this.display = display;
        this.description=description;
    }
}



@Converter(autoApply = true)
class ProcedureCategoryConverter implements AttributeConverter<ProcedureCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProcedureCategory attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public ProcedureCategory convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (ProcedureCategory category : ProcedureCategory.values()) {
            if (dbData == category.getCode()) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid ProcedureCategory code: " + dbData);
    }
}