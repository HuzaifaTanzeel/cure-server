package com.example.demo.CommonFramework.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum AllergySeverity {
    MILD(2201),
    MODERATE(2202),
    SEVERE(2203);

    private final int code;

    AllergySeverity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}



@Converter(autoApply = true)
class AllergySeverityConverter implements AttributeConverter<AllergySeverity, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AllergySeverity attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public AllergySeverity convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (AllergySeverity severity : AllergySeverity.values()) {
            if (dbData.equals(severity.getCode())) {
                return severity;
            }
        }
        throw new IllegalArgumentException("Invalid AllergySeverity code: " + dbData);
    }
}
