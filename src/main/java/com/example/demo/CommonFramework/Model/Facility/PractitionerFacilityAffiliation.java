package com.example.demo.CommonFramework.Model.Facility;

import com.example.demo.CommonFramework.Model.AdmPractitioner;
import com.example.demo.Model.Scheduling.SchedulableResource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADM_FACILITY_AFFILIATION")
@Getter
@Setter
@NoArgsConstructor
public class PractitionerFacilityAffiliation {

    @EmbeddedId
    private PractitionerFacilityAffiliationId practitionerFacilityAffiliationId;

    @Column(name="RESOURCE_ID")
    private Long resourceId;

    // Other members of the entity class

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PRACTITIONER_PID", referencedColumnName = "PARTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID", insertable = false, updatable = false)
    })
    private AdmPractitioner admPractitioner;

    @ManyToOne
    @JoinColumn(name = "FACILITY_ID", referencedColumnName = "FACILITY_ID", insertable = false, updatable = false)
    private GenFacility genFacility;

    @ManyToOne
    @JoinColumn(name = "RESOURCE_ID", referencedColumnName = "RESOURCE_ID", insertable = false, updatable = false)
    private SchedulableResource resource;


    public PractitionerFacilityAffiliation(PractitionerFacilityAffiliationId practitionerFacilityAffiliationId, AdmPractitioner admPractitioner, GenFacility genFacility, SchedulableResource resource) {
        this.practitionerFacilityAffiliationId = practitionerFacilityAffiliationId;
        this.admPractitioner = admPractitioner;
        this.genFacility = genFacility;
        this.resource=resource;
    }



    public void setPractitionerFacilityAffiliationId(PractitionerFacilityAffiliationId practitionerFacilityAffiliationId) {
        this.practitionerFacilityAffiliationId = practitionerFacilityAffiliationId;
    }
}