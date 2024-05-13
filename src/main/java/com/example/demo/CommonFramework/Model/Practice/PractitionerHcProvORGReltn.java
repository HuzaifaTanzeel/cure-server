package com.example.demo.CommonFramework.Model.Practice;

import com.example.demo.CommonFramework.Model.AdmPractitioner;
import jakarta.persistence.*;


@Entity
@Table(name = "PRACTITIONER_HC_PROV_ORG_RELTN")
public class PractitionerHcProvORGReltn {

    @EmbeddedId
    private PractitionerHcProvORGReltnId practitionerHcProvORGReltnId;

    // Other members of the entity class

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PRACTITIONER_PID", referencedColumnName = "PARTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID", insertable = false, updatable = false)
    })
    private AdmPractitioner admPractitioner;

    @ManyToOne
    @JoinColumn(name = "HC_PROV_ORG_PID", referencedColumnName = "HC_PROV_ORG_PID", insertable = false, updatable = false)
    private HealthcareProviderOrganization healthcareProviderOrganization;

    public PractitionerHcProvORGReltn() {
    }

    public PractitionerHcProvORGReltn(PractitionerHcProvORGReltnId practitionerHcProvORGReltnId, AdmPractitioner admPractitioner, HealthcareProviderOrganization healthcareProviderOrganization) {
        this.practitionerHcProvORGReltnId = practitionerHcProvORGReltnId;
        this.admPractitioner = admPractitioner;
        this.healthcareProviderOrganization = healthcareProviderOrganization;
    }

// Other getters and setters


    public PractitionerHcProvORGReltnId getPractitionerHcProvORGReltnId() {
        return practitionerHcProvORGReltnId;
    }

    public void setPractitionerHcProvORGReltnId(PractitionerHcProvORGReltnId practitionerHcProvORGReltnId) {
        this.practitionerHcProvORGReltnId = practitionerHcProvORGReltnId;
    }

    public HealthcareProviderOrganization getHealthcareProviderOrganization() {
        return healthcareProviderOrganization;
    }

    public void setHealthcareProviderOrganization(HealthcareProviderOrganization healthcareProviderOrganization) {
        this.healthcareProviderOrganization = healthcareProviderOrganization;
    }

    public AdmPractitioner getAdmPractitioner() {
        return admPractitioner;
    }

    public void setAdmPractitioner(AdmPractitioner admPractitioner) {
        this.admPractitioner = admPractitioner;
    }

    // Other getters and setters
}

