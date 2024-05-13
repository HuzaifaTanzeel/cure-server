package com.example.demo.Enumerators.Medication;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedReqIntent {
    PROPOSAL(6001, "PROPOSAL", "PROPOSAL", "The request is a suggestion made by someone/something that doesn't have an intention to ensure it occurs and without providing an authorization to act"),
    PLAN(6002, "PLAN", "PLAN", "The request represents an intention to ensure something occurs without providing an authorization for others to act."),
    ORDER(6003, "ORDER", "ORDER", "The request represents a request/demand and authorization for action"),
    OPTION(6004, "OPTION", "OPTION", "The request represents a component or option for a RequestOrchestration that establishes timing, conditionality and/or other constraints among a set of requests.");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;
}


@Converter(autoApply = true)
class MedReqIntentConverter implements AttributeConverter<MedReqIntent, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MedReqIntent intent) {
        return intent != null ? intent.getCode() : null;
    }

    @Override
    public MedReqIntent convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        for (MedReqIntent intent : MedReqIntent.values()) {
            if (code.equals(intent.getCode())) {
                return intent;
            }
        }

        throw new IllegalArgumentException("Invalid MedReqIntent code: " + code);
    }
}