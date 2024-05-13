package com.example.demo.CommonFramework.Enumerators;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

public enum PartyType {
    ORG(301),
    PERSON(302);
    // other types...

    private final int code;

    PartyType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

@Converter(autoApply = true)
class PartyTypeConvertor implements AttributeConverter<PartyType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PartyType partyType) {
        return partyType != null ? partyType.getCode() : null;
    }

    @Override
    public PartyType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (PartyType partyType : PartyType.values()) {
            if (code == partyType.getCode()) {
                return partyType;
            }
        }
        throw new IllegalArgumentException("Invalid PartyType code: " + code);
    }
}