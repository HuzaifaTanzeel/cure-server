package com.example.demo.CommonFramework.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum AllergyCategory {
    FOOD(2001),
    MEDICATION(2002),
    ENVIRONMENT(2003),
    BIOLOGIC(2004);

    private Integer code;

    AllergyCategory(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

@Converter(autoApply = true)
class AllergyCategoryConverter implements AttributeConverter<AllergyCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AllergyCategory attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public AllergyCategory convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (AllergyCategory category : AllergyCategory.values()) {
            if (dbData.equals(category.getCode())) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid AllergyCategory code: " + dbData);
    }
}
