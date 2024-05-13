package com.example.demo.Enumerators.Medication;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedReqStatus {
    ACTIVE(1701, "ACTIVE", "ACTIVE", "The prescription is 'actionable', but not all actions that are implied by it have occurred yet."),
    ON_HOLD(1702, "ON-HOLD", "ON-HOLD", "Actions implied by the prescription are to be temporarily halted, but are expected to continue later. May also be called 'suspended'."),
    CANCELLED(1703, "CANCELLED", "CANCELLED", "The prescription has been withdrawn before any administrations have occurred."),
    COMPLETED(1704, "COMPLETED", "COMPLETED", "All actions that are implied by the prescription have occurred."),
    STOPPED(1705, "STOPPED", "STOPPED", "Actions implied by the prescription are to be permanently halted, before all of the administrations occurred. This should not be used if the original order was entered in error."),
    DRAFT(1706, "DRAFT", "DRAFT", "The prescription is not yet 'actionable', e.g. it is a work in progress, requires sign-off, verification or needs to be run through decision support process."),
    ENTERED_IN_ERROR(1707, "ENTERED-IN-ERROR", "ENTERED-IN-ERROR", null),
    UNKNOWN(1708, "UNKNOWN", "UNKNOWN", "The authoring/source system does not know which of the status values currently applies for this observation.");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}


@Converter(autoApply = true)
class MedReqStatusConverter implements AttributeConverter<MedReqStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MedReqStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public MedReqStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (MedReqStatus status : MedReqStatus.values()) {
            if (code.equals(status.getCode())) {
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid MedReqStatus code: " + code);
    }
}