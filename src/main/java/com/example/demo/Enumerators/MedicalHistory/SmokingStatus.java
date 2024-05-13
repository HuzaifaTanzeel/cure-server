package com.example.demo.Enumerators.MedicalHistory;



import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum SmokingStatus {
    NEVER("2701", "NEVER", "NEVER SMOKE"),
    CHAIN_SMOKER("2702", "CHAIN SMOKER", "CHAIN SMOKER"),
    NORMAL("2703", "NORMAL", "NORMAL SMOKE"),
    QUIT("2704", "QUIT", "QUIT SMOKING");

    private final String code;
    private final String display;
    private final String definition;

    SmokingStatus(String code, String display, String definition) {
        this.code = code;
        this.display = display;
        this.definition = definition;
    }
}

@Converter(autoApply = true)
class SmokingStatusConverter implements AttributeConverter<SmokingStatus, String> {

    @Override
    public String convertToDatabaseColumn(SmokingStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public SmokingStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        for (SmokingStatus status : SmokingStatus.values()) {
            if (dbData.equals(status.getCode()) || dbData.equals(status.getDisplay()) || dbData.equals(status.getDefinition())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Smoking Status: " + dbData);
    }
}
