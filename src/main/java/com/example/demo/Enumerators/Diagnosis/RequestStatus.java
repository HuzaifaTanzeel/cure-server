package com.example.demo.Enumerators.Diagnosis;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    DRAFT(901, "DRAFT", "DRAFT", "The request has been created but is not yet complete or ready for action."),
    ACTIVE(902, "ACTIVE", "ACTIVE", "The request is in force and ready to be acted upon."),
    ON_HOLD(903, "ON-HOLD", "ON-HOLD", "The request (and any implicit authorization to act) has been temporarily withdrawn but is expected to resume in the future."),
    REVOKED(904, "REVOKED", "REVOKED", "The request (and any implicit authorization to act) has been terminated prior to the known full completion of the intended actions. No further activity should occur."),
    COMPLETED(905, "COMPLETED", "COMPLETED", "The activity described by the request has been fully performed. No further activity will occur."),
    ENTERED_IN_ERROR(906, "ENTERED-IN-ERROR", "ENTERED-IN-ERROR", "This request should never have existed and should be considered 'void'. (It is possible that real-world decisions were based on it. If real-world activity has occurred, the status should be 'revoked' rather than 'entered-in-error'.)"),
    UNKNOWN(907, "UNKNOWN", "UNKNOWN", "The authoring/source system does not know which of the status values currently applies for this request.");

    private final int code;
    private final String description;
    private final String display;
    private final String definition;

}


@Converter(autoApply = true)
class RequestStatusConverter implements AttributeConverter<RequestStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RequestStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public RequestStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (RequestStatus status : RequestStatus.values()) {
            if (dbData.equals(status.getCode())) {
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid RequestStatus code: " + dbData);
    }
}