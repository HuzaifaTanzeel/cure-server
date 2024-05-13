package com.example.demo.Enumerators.Medication;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedUsgStatus {
    RECORDED(6201, "RECORDED", "RECORDED", "The action of recording the medication statement is finished."),
    ENTERED_IN_ERROR(6202, "ENTERED IN ERROR", "ERROR", "Some of the actions that are implied by the medication usage may have occurred. For example, the patient may have taken some of the medication. Clinical decision support systems should take this status into account."),
    DRAFT(6203, "DRAFT", "DRAFT", "The medication usage is draft or preliminary.");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}



@Converter(autoApply = true)
class MedUsgStatusConverter implements AttributeConverter<MedUsgStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MedUsgStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public MedUsgStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (MedUsgStatus status : MedUsgStatus.values()) {
            if (code.equals(status.getCode())) {
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid MedUsgStatus code: " + code);
    }
}
