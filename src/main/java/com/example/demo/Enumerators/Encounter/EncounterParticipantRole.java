package com.example.demo.Enumerators.Encounter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum EncounterParticipantRole {
    PRIMARY_PERFORMER(5801, "PPRF", "PRIMARY PERFORMER"),
    SECONDARY_PERFORMER(5802, "SPRF", "SECONDARY PERFORMER"),
    PARTICIPANT(5803, "PART", "PARTICIPANT");

    private final int code;
    private final String display;
    private final String definition;

    EncounterParticipantRole(int code, String display, String definition) {
        this.code = code;
        this.display = display;
        this.definition = definition;
    }

}

@Converter(autoApply = true)
class EncounterParticipantRoleConverter implements AttributeConverter<EncounterParticipantRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EncounterParticipantRole attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public EncounterParticipantRole convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (EncounterParticipantRole role : EncounterParticipantRole.values()) {
            if (dbData == role.getCode()) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid EncounterParticipantRole code: " + dbData);
    }
}

