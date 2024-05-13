package com.example.demo.Model.Scheduling;


import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
import com.example.demo.Model.Medication.MedRequestPatientDiagnosisId;
import com.example.demo.Model.Medication.MedicationRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "SCH_SCH_RESOURCE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResource {

    @EmbeddedId
    ScheduleResourceId scheduleResourceId;


    @ManyToOne
    @MapsId("scheduleId")
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;


    //@Id
    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "RESOURCE_ID")
    private SchedulableResource resource;

    public ScheduleResource(Schedule schedule, SchedulableResource resource) {
        this.scheduleResourceId=new ScheduleResourceId(schedule.getScheduleId(),resource.getResourceId());
        this.schedule = schedule;
        this.resource = resource;
    }



}
