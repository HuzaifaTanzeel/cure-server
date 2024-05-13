package com.example.demo.CommonFramework.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum CondVerfStatus {
    UNCONFIRM(4401, "UNCONFIRM", "UNCONFIRMED", "There is not sufficient diagnostic and/or clinical evidence to treat this as a confirmed condition."),
    PROVISION(4402, "PROVISION", "PROVISIONAL", "This is a tentative diagnosis - still a candidate that is under consideration."),
    DIFFERENT(4403, "DIFFERENT", "DIFFERENTIAL", "One of a set of potential (and typically mutually exclusive) diagnoses asserted to further guide the diagnostic process and preliminary treatment."),
    CONFIRMED(4404, "CONFIRMED", "CONFIRMED", "There is sufficient diagnostic and/or clinical evidence to treat this as a confirmed condition."),
    REFUTED(4405, "REFUTED", "REFUTED", "This condition has been ruled out by diagnostic and clinical evidence."),
    ERROR(4406, "ERROR", "ENTERED-IN-ERROR", "The statement was entered in error and is not valid.");

    private final int code;
    private final String display;
    private final String definition;
    private final String description;

    CondVerfStatus(int code, String display, String definition, String description) {
        this.code = code;
        this.display = display;
        this.definition = definition;
        this.description = description;
    }

}


@Converter(autoApply = true)
class CondVerfStatusConverter implements AttributeConverter<CondVerfStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CondVerfStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public CondVerfStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (CondVerfStatus status : CondVerfStatus.values()) {
            if (dbData == status.getCode()) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid CondVerfStatus code: " + dbData);
    }
}