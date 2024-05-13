package com.example.demo.CommonFramework.Enumerators;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;



public enum AllergyStatus {
    ACTIVE(2101),
    INACTIVE(2102),
    RESOLVED(2103);

    private final int code;

    AllergyStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


@Converter(autoApply = true)
class AllergyStatusConverter implements AttributeConverter<AllergyStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AllergyStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public AllergyStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (AllergyStatus status : AllergyStatus.values()) {
            if (dbData.equals(status.getCode())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid AllergyStatus code: " + dbData);
    }
}





