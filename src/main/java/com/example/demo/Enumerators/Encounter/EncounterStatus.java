package com.example.demo.Enumerators.Encounter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum EncounterStatus {
    PLANNED(1, "PLANNED", "The Encounter has not yet started."),
    IN_PROGRESS(2, "IN-PROGRESS", "The Encounter has begun and the patient is present / the practitioner and the patient are meeting."),
    ON_HOLD(3, "ON HOLD", "The Encounter has begun, but is currently on hold, e.g. because the patient is temporarily on leave."),
    COMPLETED(4, "COMPLETED", "The Encounter has ended."),
    CANCELLED(5, "CANCELLED", "The Encounter has ended before it has begun."),
    ENTERED_IN_ERROR(6, "ENTERED-IN-ERROR", "This instance should not have been part of this patient's medical record."),
    UNKNOWN(7, "UNKNOWN", "The encounter status is unknown. Note that 'unknown' is a value of last resort and every attempt should be made to provide a meaningful value other than 'unknown'.");

    private final int code;
    private final String display;
    private final String definition;

    EncounterStatus(int code, String display, String definition) {
        this.code = code;
        this.display = display;
        this.definition = definition;
    }

}

@Converter(autoApply = true)
class EncounterStatusConverter implements AttributeConverter<EncounterStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EncounterStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public EncounterStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (EncounterStatus status : EncounterStatus.values()) {
            if (dbData == status.getCode()) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid EncounterStatus code: " + dbData);
    }
}

