package com.example.demo.Model.Scheduling;




import com.example.demo.CommonFramework.Model.Facility.PractitionerFacilityAffiliation;
import com.example.demo.Enumerators.Scheduling.ResourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SCH_SCHEDULABLE_RESOURCE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulableResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESOURCE_ID")
    private Long resourceId;

    @Column(name = "RESOURCE_TYPE_CD")
    private ResourceType resourceTypeCode;

    @Column(name = "ENTITY_NAME", length = 40)
    private String entityName;

    @Column(name = "ENTITY_ID", length = 40)
    private String entityId;

    @Column(name = "ACTIVE_IND", length = 1)
    private char activeIndicator;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilitySchedule> availabilityScheduleList=new ArrayList<>();

    public void addAvailabilitySchedule(AvailabilitySchedule availabilitySchedule) {
        availabilityScheduleList.add(availabilitySchedule);
        availabilitySchedule.setResource(this);
    }

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PractitionerFacilityAffiliation> practitionerFacilityAffiliationList=new ArrayList<>();

    public void addPractitionerFacilityAffiliation(PractitionerFacilityAffiliation practitionerFacilityAffiliation) {
        practitionerFacilityAffiliationList.add(practitionerFacilityAffiliation);
        practitionerFacilityAffiliation.setResource(this);
    }


    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduleResource> scheduleResourceList=new ArrayList<>();

    public void addScheduleResource(ScheduleResource scheduleResource) {
        scheduleResourceList.add(scheduleResource);
        scheduleResource.setResource(this);
    }

    // Constructors, getters, and setters
}
