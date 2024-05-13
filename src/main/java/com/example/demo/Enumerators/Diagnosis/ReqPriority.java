package com.example.demo.Enumerators.Diagnosis;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReqPriority {
    ROUTINE(1801, "ROUTINE", "ROUTINE", "The request has normal priority."),
    URGENT(1802, "URGENT", "URGENT", "The request should be actioned promptly - higher priority than routine."),
    ASAP(1803, "ASAP", "ASAP", "The request should be actioned as soon as possible - higher priority than urgent."),
    STAT(1804, "STAT", "STAT", "The request should be actioned immediately - highest possible priority. E.g. an emergency.");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}



@Converter(autoApply = true)
class ReqPriorityConverter implements AttributeConverter<ReqPriority, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReqPriority attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public ReqPriority convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (ReqPriority priority : ReqPriority.values()) {
            if (dbData.equals(priority.getCode())) {
                return priority;
            }
        }

        throw new IllegalArgumentException("Invalid ReqPriority code: " + dbData);
    }
}