package com.example.demo.Enumerators.AppointmentManagement;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum AppointmentType {
    CHECKUP(4101, "CHECKUP", "A routine check-up, such as an annual physical"),
    EMERGENCY(4102, "EMERGENCY", "Emergency appointment"),
    FOLLOWUP(4103, "FOLLOWUP", "A follow up visit from a previous appointment"),
    ROUTINE(4104, "ROUTINE", "Routine appointment - default if not valued"),
    WALKIN(4105, "WALKIN", "A previously unscheduled walk-in visit");

    private final int code;
    private final String abbreviation;
    private final String description;
}

@NoArgsConstructor
@Converter(autoApply = true)
class AppointmentTypeConverter implements AttributeConverter<AppointmentType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AppointmentType attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public AppointmentType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (AppointmentType type : AppointmentType.values()) {
            if (dbData.equals(type.getCode())) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid AppointmentType code: " + dbData);
    }
}