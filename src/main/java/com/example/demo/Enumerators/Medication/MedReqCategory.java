package com.example.demo.Enumerators.Medication;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedReqCategory {
    INPATIENT(1501, "INPATIENT", "Inpatient", "Includes requests for medications to be administered or consumed in an inpatient or acute care setting"),
    OUTPATIENT(1502, "OUTPATIENT", "Outpatient", "Includes requests for medications to be administered or consumed in an outpatient setting (for example, Emergency Department, Outpatient Clinic, Outpatient Surgery, Doctor's office)"),
    COMMUNITY(1503, "COMMUNITY", "Community", "Includes requests for medications to be administered or consumed by the patient in their home (this would include long term care or nursing homes, hospices, etc.)"),
    DISCHARGE(1504, "DISCHARGE", "Discharge", "Includes requests for medications created when the patient is being released from a facility");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}


@Converter(autoApply = true)
class MedReqCategoryConverter implements AttributeConverter<MedReqCategory, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MedReqCategory category) {
        return category != null ? category.getCode() : null;
    }

    @Override
    public MedReqCategory convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (MedReqCategory category : MedReqCategory.values()) {
            if (code.equals(category.getCode())) {
                return category;
            }
        }

        throw new IllegalArgumentException("Invalid MedReqCategory code: " + code);
    }
}
