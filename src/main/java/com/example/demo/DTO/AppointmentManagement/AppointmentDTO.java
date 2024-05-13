package com.example.demo.DTO.AppointmentManagement;

import com.example.demo.Enumerators.AppointmentManagement.AppointmentStatus;
import com.example.demo.Enumerators.AppointmentManagement.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private String patientName;
    private String practitionerName;
    private String clinicName;

    private Long appointmentId;
    private Long patientId;
    private Long partyRoleTypeId;
    private AppointmentType appointmentTypeCd;
    private Long serviceId;
    private LocalDate appointmentDate;
    private LocalTime appointmentStartTime;
    private LocalTime appointmentEndTime;
    private AppointmentStatus appointmentStatusCd;
    private String appointmentReason;
    private Long availabilityId;
}