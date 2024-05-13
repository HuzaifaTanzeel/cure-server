package com.example.demo.Enumerators.MedicalHistory;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FamilyHistoryStatus {
    PARTIAL(2301, "PARTIAL", "Partial", "Some health information is known and captured, but not complete - see notes for details."),
    COMPLETED(2302, "COMPLETED", "Completed", "All available related health information is captured as of the date (and possibly time) when the family member history was taken."),
    ENTERED_IN_ERROR(2303, "ENTERED-IN-ERROR", "Entered in Error", "This instance should not have been part of this patient's medical record."),
    HEALTH_UNKNOWN(2304, "HEALTH-UNKNOWN", "Health Unknown", "Health information for this family member is unavailable/unknown.");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}


@Converter(autoApply = true)
class FamilyHistoryStatusConverter implements AttributeConverter<FamilyHistoryStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FamilyHistoryStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public FamilyHistoryStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (FamilyHistoryStatus status : FamilyHistoryStatus.values()) {
            if (dbData.equals(status.getCode())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid FamilyHistoryStatus code: " + dbData);
    }
}