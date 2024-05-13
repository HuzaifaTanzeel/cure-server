package com.example.demo.Enumerators.Diagnosis;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestIntent {
    PROPOSAL(1001, "PROPOSAL", "PROPOSAL", "The request is a suggestion made by someone/something that does not have an intention to ensure it occurs and without providing an authorization to act."),
    PLAN(1002, "PLAN", "PLAN", "The request represents an intention to ensure something occurs without providing an authorization for others to act."),
    DIRECTIVE(1003, "DIRECTIVE", "DIRECTIVE", "The request represents a legally binding instruction authored by a Patient or RelatedPerson."),
    ORDER(1004, "ORDER", "ORDER", "The request represents a request/demand and authorization for action by a Practitioner.");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;

}


@Converter(autoApply = true)
class RequestIntentConverter implements AttributeConverter<RequestIntent, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RequestIntent attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public RequestIntent convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (RequestIntent intent : RequestIntent.values()) {
            if (dbData.equals(intent.getCode())) {
                return intent;
            }
        }

        throw new IllegalArgumentException("Invalid RequestIntent code: " + dbData);
    }
}