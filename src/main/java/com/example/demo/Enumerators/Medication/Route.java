package com.example.demo.Enumerators.Medication;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Route {
    TOPICAL_RO(1601, "TOPICAL RO", "TOPICAL ROUTE", "How drug should enter body"),
    VIGINAL_US(1602, "VIGINAL US", "VIGINAL USE", "Viginal Use"),
    ORAL(1603, "ORAL", "ORAL", "ORAL"),
    INTRAMUSCULAR(1604, "INTRAMUSCU", "INTRAMUSCULAR", "INTRAMUSCULAR USE");

    private final int code;
    private final String abbreviation;
    private final String name;
    private final String description;
}


@Converter(autoApply = true)
class RouteConverter implements AttributeConverter<Route, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Route attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public Route convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (Route route : Route.values()) {
            if (dbData.equals(route.getCode())) {
                return route;
            }
        }

        throw new IllegalArgumentException("Invalid Route code: " + dbData);
    }
}