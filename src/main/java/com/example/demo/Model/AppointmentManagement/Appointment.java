package com.example.demo.Model.AppointmentManagement;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.Enumerators.AppointmentManagement.AppointmentStatus;
import com.example.demo.Enumerators.AppointmentManagement.AppointmentType;
import com.example.demo.Model.Scheduling.AvailabilitySchedule;
import com.example.demo.Model.Scheduling.AvailabilityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APPOINTMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Long appointmentId;

    @Column(name = "PATIENT_ID")
    private Long patientId;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;


    @Column(name = "APPOINTMENT_TYPE_CD")
    private AppointmentType appointmentTypeCd;

    @Column(name = "SERVICE_ID")
    private Long serviceId;

    @Column(name = "APPOINTMENT_DT")
    private LocalDate appointmentDate;

    @Column(name = "APPOINTMENT_START_TM")
    private LocalTime appointmentStartTime;

    @Column(name = "APPOINTMENT_END_TM")
    private LocalTime appointmentEndTime;

    @Column(name = "APPOINTMENT_STATUS_CD")
    private AppointmentStatus appointmentStatusCd;

    @Column(name = "APPOINTMENT_REASON")
    private String appointmentReason;


    @Column(name = "AVAILABILITY_ID")
    private Long availabilityId;


    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Encounter> encounterList=new ArrayList<>();

    public void addEncounter(Encounter encounter) {
        encounterList.add(encounter);
        encounter.setAppointment(this);
    }

    @ManyToOne
    @JoinColumn(name = "AVAILABILITY_ID", referencedColumnName = "AVAILABILITY_ID",insertable = false, updatable = false)
    private AvailabilitySchedule availabilitySchedule;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PARTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID", insertable = false, updatable = false)
    })
    private AdmPatient admPatient;


    // Constructors, getters, and setters
}



