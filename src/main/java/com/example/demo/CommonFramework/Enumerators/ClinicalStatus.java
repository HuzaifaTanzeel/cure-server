package com.example.demo.CommonFramework.Enumerators;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum ClinicalStatus {
    ACTIVE(4301, "ACTIVE", "The subject is currently experiencing the symptoms of the condition or there is evidence of the condition"),
    RECURRENCE(4302, "RECURRENCE", "The subject is experiencing a re-occurrence or repeating of a previously resolved condition, e.g. urinary tract infection, pancreatitis, cholangitis, conjunctivitis"),
    RELAPSE(4303, "RELAPSE", "e.g. relapse of cancer, multiple sclerosis"),
    INACTIVE(4304, "INACTIVE", "The subject is no longer experiencing the symptoms of the condition or there is no longer evidence of the condition."),
    REMISSION(4305, "REMISSION", "The subject is no longer experiencing the symptoms of the condition, but there is a risk of the symptoms returning."),
    RESOLVED(4306, "RESOLVED", "The subject is no longer experiencing the symptoms of the condition and there is a negligible perceived risk of the symptoms returning");

    private final int code;
    private final String display;
    private final String definition;

    ClinicalStatus(int code, String display, String definition) {
        this.code = code;
        this.display = display;
        this.definition = definition;
    }
}

@Converter(autoApply = true)
class ClinicalStatusConverter implements AttributeConverter<ClinicalStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClinicalStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public ClinicalStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (ClinicalStatus status : ClinicalStatus.values()) {
            if (code == status.getCode()) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ClinicalStatus code: " + code);
    }
}
