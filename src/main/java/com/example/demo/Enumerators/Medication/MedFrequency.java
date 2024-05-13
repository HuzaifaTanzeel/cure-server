package com.example.demo.Enumerators.Medication;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedFrequency {
    ONCE_PER_DAY(6101, "ONCE PER DAY", "ONCE PER DAY", null),
    TWICE_PER_DAY(6102, "TWICE PER DAY", "TWICE PER DAY", "TWICE PER DAY"),
    EVERY_TWO_HOUR(6103, "EVERY TWO HOUR", "EVERY TWO HOUR", "EVERY TWO HOUR"),
    EVERY_FOUR_HOUR(6104, "EVERY FOUR HOUR", "EVERY FOUR HOUR", "EVERY FOUR HOUR"),
    EVERY_8_HOUR(6105, "EVERY 8 HOUR", "EVERY 8 HOURS", "EVERY 8 HOUR");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}

@Converter(autoApply = true)
class MedFrequencyConverter implements AttributeConverter<MedFrequency, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MedFrequency frequency) {
        return frequency != null ? frequency.getCode() : null;
    }

    @Override
    public MedFrequency convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (MedFrequency frequency : MedFrequency.values()) {
            if (code.equals(frequency.getCode())) {
                return frequency;
            }
        }

        throw new IllegalArgumentException("Invalid MedFrequency code: " + code);
    }
}

