package com.example.demo.Enumerators.AppointmentManagement;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum AppointmentStatus {
    PROPOSED(4201, "PROPOSED", "None of the participant(s) have finalized their acceptance of the appointment request, and the start/end time might not be set yet."),
    PENDING(4202, "PENDING", "Some or all of the participant(s) have not finalized their acceptance of the appointment request."),
    BOOKED(4203, "BOOKED", "All participant(s) have been considered and the appointment is confirmed to go ahead at the date/times specified."),
    ARRIVED(4204, "ARRIVED", "The patient/patients has/have arrived and is/are waiting to be seen"),
    FULFILLED(4205, "FULFILLED", "This appointment has completed and may have resulted in an encounter."),
    CANCELLED(4206, "CANCELLED", "The appointment has been cancelled."),
    NOSHOW(4207, "NOSHOW", "Some or all of the participant(s) have not/did not appear for the appointment (usually the patient)."),
    CHECKED_IN(4208, "CHECKED-IN", "When checked in, all pre-encounter administrative work is complete, and the encounter may begin. (where multiple patients are involved, they are all present)"),
    ERROR(4209, "ENTER-IN-ERROR", "This instance should not have been part of this patient's medical record.");

    private final int code;
    private final String abbreviation;
    private final String description;
}

@NoArgsConstructor
@Converter(autoApply = true)
class AppointmentStatusConverter implements AttributeConverter<AppointmentStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AppointmentStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public AppointmentStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (dbData.equals(status.getCode())) {
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid AppointmentStatus code: " + dbData);
    }
}