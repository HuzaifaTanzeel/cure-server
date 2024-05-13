package com.example.demo.Enumerators.Encounter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum EncounterType {
    OFFICE_VISIT(201, "OFFICE_VST", "OFFICE VISIT"),
    NURSE_VISIT(202, "NURSE_VST", "NURSE VISIT"),
    ORDER_ONLY(203, "ORDER_ONLY", "ORDER ONLY");

    private final int code;
    private final String codeSystem;
    private final String display;

    EncounterType(int code, String codeSystem, String display) {
        this.code = code;
        this.codeSystem = codeSystem;
        this.display = display;
    }

}

@Converter(autoApply = true)
class EncounterTypeConverter implements AttributeConverter<EncounterType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EncounterType attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public EncounterType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (EncounterType encounterType : EncounterType.values()) {
            if (dbData == encounterType.getCode()) {
                return encounterType;
            }
        }
        throw new IllegalArgumentException("Invalid EncounterType code: " + dbData);
    }
}

