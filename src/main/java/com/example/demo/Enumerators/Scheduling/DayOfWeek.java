package com.example.demo.Enumerators.Scheduling;

import com.example.demo.Enumerators.Medication.DrugForm;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DayOfWeek {
    MONDAY(6901, "MON", "MONDAY", "Monday"),
    TUESDAY(6902, "TUE", "TUESDAY", "Tuesday"),
    WEDNESDAY(6903, "WED", "WEDNESDAY", "Wednesday"),
    THURSDAY(6904, "THU", "THURSDAY", "Thursday"),
    FRIDAY(6905, "FRI", "FRIDAY", "Friday"),
    SATURDAY(6906, "SAT", "SATURDAY", "Saturday"),
    SUNDAY(6907, "SUN", "SUNDAY", "Sunday");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}

@Converter(autoApply = true)
class DayOfWeekConverter implements AttributeConverter<DayOfWeek, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DayOfWeek day) {
        return day != null ? day.getCode() : null;
    }

    @Override
    public DayOfWeek convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (DayOfWeek day : DayOfWeek.values()) {
            if (code.equals(day.getCode())) {
                return day;
            }
        }

        throw new IllegalArgumentException("Invalid DayOfWeek code: " + code);
    }
}
