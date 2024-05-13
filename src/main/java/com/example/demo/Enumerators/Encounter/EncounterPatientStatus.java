package com.example.demo.Enumerators.Encounter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum EncounterPatientStatus {
    ARRIVED(5701, "ARRIVED", "Arrived"),
    TRIAGED(5702, "TRIAGED", "Triaged"),
    ON_LEAVE(5703, "ON-LEAVE", "On Leave"),
    DEPARTED(5704, "DEPARTED", "Departed");

    private final int code;
    private final String display;
    private final String definition;

    EncounterPatientStatus(int code, String display, String definition) {
        this.code = code;
        this.display = display;
        this.definition = definition;
    }

}

@Converter(autoApply = true)
class EncounterPatientStatusConverter implements AttributeConverter<EncounterPatientStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EncounterPatientStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public EncounterPatientStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (EncounterPatientStatus status : EncounterPatientStatus.values()) {
            if (dbData == status.getCode()) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid EncounterPatientStatus code: " + dbData);
    }
}

