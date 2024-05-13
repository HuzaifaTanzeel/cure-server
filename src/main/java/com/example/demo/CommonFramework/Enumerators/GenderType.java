package com.example.demo.CommonFramework.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum GenderType {
    MALE(2501), FEMALE(2502);
    private Integer code;

    private GenderType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

@Converter(autoApply = true)
class GenderTypeConvertor implements AttributeConverter<GenderType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(GenderType genderType) {
        return genderType != null ? genderType.getCode() : null;
    }

    @Override
    public GenderType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (GenderType genderType : GenderType.values()) {
            if (dbData.equals(genderType.getCode())) {
                return genderType;
            }
        }
        throw new IllegalArgumentException("Invalid GenderType code: " + dbData);
    }
}