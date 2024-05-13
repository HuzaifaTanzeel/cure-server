package com.example.demo.CommonFramework.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum CondSeverity {
    SEVERE(4601, "SEVERE", "SEVERE"),
    MODERATE(4602, "MODERATE", "MODERATE"),
    MILD(4603, "MILD", "MILD");

    private final int code;
    private final String display;
    private final String definition;

    CondSeverity(int code, String display, String definition) {
        this.code = code;
        this.display = display;
        this.definition = definition;
    }

}


@Converter(autoApply = true)
class CondSeverityConverter implements AttributeConverter<CondSeverity, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CondSeverity attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public CondSeverity convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (CondSeverity severity : CondSeverity.values()) {
            if (dbData.equals(severity.getCode())) {
                return severity;
            }
        }
        throw new IllegalArgumentException("Invalid CondSeverity code: " + dbData);
    }
}
