package com.example.demo.Model.Scheduling;

import com.example.demo.Enumerators.Scheduling.DayOfWeek;
import com.example.demo.Model.AppointmentManagement.Appointment;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.MedicalHistory.ClinicalProcedure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AVAILABILITY_SCHEDULE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilitySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVAILABILITY_ID")
    private Long availabilityId;

    @Column(name = "RESOURCE_ID")
    private Long resourceId;

    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;

    @Column(name = "WEEK_DAY_CD")
    private DayOfWeek weekDayCode;

    @Column(name = "START_TIME_TM")
    private LocalTime startTime;

    @Column(name = "END_TIME_TM")
    private LocalTime endTime;

    @Column(name = "AVAILABILITY_TYPE_ID")
    private Long availabilityTypeId;

    @Column(name = "NO_OF_SLOTS")
    private int numberOfSlots;

    @Column(name = "SLOT_DURATION")
    private int slotDuration;


    @ManyToOne
    @JoinColumn(name = "RESOURCE_ID", referencedColumnName = "RESOURCE_ID",insertable = false, updatable = false)
    private SchedulableResource resource;

    @ManyToOne
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "SCHEDULE_ID",insertable = false, updatable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "AVAILABILITY_TYPE_ID", referencedColumnName = "AVAILABILITY_TYPE_ID",insertable = false, updatable = false)
    private AvailabilityType availabilityType;

    @OneToMany(mappedBy = "availabilitySchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentList=new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        appointmentList.add(appointment);
        appointment.setAvailabilitySchedule(this);
    }
}
