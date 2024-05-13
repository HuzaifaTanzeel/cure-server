package com.example.demo.Model.Scheduling;

import com.example.demo.CommonFramework.Model.Practice.GenOrganization;
import com.example.demo.Model.Diagnosis.ClnServiceRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SCH_SCHEDULE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;

    @Column(name = "ORG_PID")
    private Long orgPid;

    @Column(name = "NAME", length = 40)
    private String name;

    @Column(name = "EFFECTIVE_START_DT")
    private LocalDate effectiveStartDate;

    @Column(name = "EFFECTIVE_END_DT")
    private LocalDate effectiveEndDate;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilitySchedule> availabilityScheduleList=new ArrayList<>();

    public void addAvailabilitySchedule(AvailabilitySchedule availabilitySchedule) {
        availabilityScheduleList.add(availabilitySchedule);
        availabilitySchedule.setSchedule(this);
    }

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NonAvailabilitySchedule> nonAvailabilityScheduleList=new ArrayList<>();

    public void addNonAvailabilitySchedule(NonAvailabilitySchedule nonAvailabilitySchedule) {
        nonAvailabilityScheduleList.add(nonAvailabilitySchedule);
        nonAvailabilitySchedule.setSchedule(this);
    }

    @ManyToOne
    @JoinColumn(name = "ORG_PID", referencedColumnName = "PARTY_ID", nullable = false,insertable = false,updatable = false)
    private GenOrganization organization;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleResource> scheduleResourceList=new ArrayList<>();

    public void addScheduleResource(ScheduleResource scheduleResource) {
        scheduleResourceList.add(scheduleResource);
        scheduleResource.setSchedule(this);
    }

}