package com.example.demo.Enumerators.Diagnosis;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServReqCategory {
    LAB_PROC(1101, "LAB_PROC", "Laboratory procedure", "Laboratory procedure"),
    IMAGING(1102, "IMAGING", "Imaging", "Imaging"),
    SURG_PROC(1103, "SURG_PROC", "Surgical procedure", "Surgical procedure"),
    COUNSELLING(1104, "COUNSELLING", "Counselling", "Counselling"),
    EDUCATION(1105, "EDUCATION", "Education", "Education");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}

@Converter(autoApply = true)
class ServReqCategoryConverter implements AttributeConverter<ServReqCategory, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ServReqCategory attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public ServReqCategory convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (ServReqCategory category : ServReqCategory.values()) {
            if (dbData.equals(category.getCode())) {
                return category;
            }
        }

        throw new IllegalArgumentException("Invalid ServReqCategory code: " + dbData);
    }
}