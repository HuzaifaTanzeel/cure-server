package com.example.demo.Enumerators.Medication;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DrugForm {
    SUSPENSION(1401, "SUSPENSION", "Suspension", "Suspension"),
    CAPLET(1402, "CAPLET", "Caplet", "Caplet"),
    TABLET(1403, "TABLET", "Tablet", "Tablet"),
    AMPULE(1404, "AMPULE", "Ampule", "Ampule");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}


@Converter(autoApply = true)
class DrugFormConverter implements AttributeConverter<DrugForm, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DrugForm form) {
        return form != null ? form.getCode() : null;
    }

    @Override
    public DrugForm convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (DrugForm form : DrugForm.values()) {
            if (code.equals(form.getCode())) {
                return form;
            }
        }

        throw new IllegalArgumentException("Invalid DrugForm code: " + code);
    }
}