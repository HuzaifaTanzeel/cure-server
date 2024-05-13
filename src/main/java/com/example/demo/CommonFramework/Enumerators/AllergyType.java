package com.example.demo.CommonFramework.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum AllergyType {
    ALLERGY(1901),
    INTOLERANCE(1902);

    private final int code;

    AllergyType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


@Converter(autoApply = true)
class AllergyTypeConverter implements AttributeConverter<AllergyType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AllergyType attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public AllergyType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (AllergyType type : AllergyType.values()) {
            if (dbData.equals(type.getCode())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid AllergyType code: " + dbData);
    }
}
