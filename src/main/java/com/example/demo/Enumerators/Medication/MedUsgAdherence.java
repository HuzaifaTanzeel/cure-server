package com.example.demo.Enumerators.Medication;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedUsgAdherence {
    TAKING(6301, "TAKING", "TAKING", "The medication is being taken."),
    NOT_TAKING(6302, "NOT TAKING", "NOT TAKING", "The medication is not being taken."),
    UNKNOWN(6303, "UNKNOWN", "UNKNOWN", "Whether the medication is being taken or not is not currently known.");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}


@Converter(autoApply = true)
class MedUsgAdherenceConverter implements AttributeConverter<MedUsgAdherence, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MedUsgAdherence adherence) {
        return adherence != null ? adherence.getCode() : null;
    }

    @Override
    public MedUsgAdherence convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (MedUsgAdherence adherence : MedUsgAdherence.values()) {
            if (code.equals(adherence.getCode())) {
                return adherence;
            }
        }

        throw new IllegalArgumentException("Invalid MedUsgAdherence code: " + code);
    }
}