package com.example.demo.Enumerators.Scheduling;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceType {
    PRACT(6801, "PRACT", "PRACTITIONER", "Practitioner Resource"),
    FACILITY(6802, "FACILITY", "FACILITY", "Facility Resource"),
    DEVICE(6803, "DEVICE", "DEVICE", "Device Resource"),
    PRACT_FAC(6804, "PRACT_FAC", "PRACT FACILITY", "Schedule of Practitioner at Facility");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}

@Converter(autoApply = true)
class ResourceTypeConverter implements AttributeConverter<ResourceType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ResourceType resourceType) {
        return resourceType != null ? resourceType.getCode() : null;
    }

    @Override
    public ResourceType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (ResourceType resourceType : ResourceType.values()) {
            if (code.equals(resourceType.getCode())) {
                return resourceType;
            }
        }

        throw new IllegalArgumentException("Invalid ResourceType code: " + code);
    }
}
