package com.example.demo.CommonFramework.Model.Practice;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HEALTHCARE_PROVIDER_ORG")
@PrimaryKeyJoinColumn(name = "HC_PROV_ORG_PID")
public class HealthcareProviderOrganization extends GenOrganization {

    @Column(name = "HC_PROV_NAME")
    private String providerName;

    @Column(name = "BRIEF")
    private String brief;



    @OneToMany(mappedBy = "healthcareProviderOrganization",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PractitionerHcProvORGReltn> practitionerHcProvORGReltns = new ArrayList<>();

    // Constructors, getters, and setters

    public HealthcareProviderOrganization() {
    }

    public HealthcareProviderOrganization(int partyTypeCd, int orgTypeCd, String providerName) {
        super(partyTypeCd, orgTypeCd);
        this.providerName = providerName;

    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public List<PractitionerHcProvORGReltn> getPractitionerHcProvORGReltns() {
        return practitionerHcProvORGReltns;
    }

    public void setPractitionerHcProvORGReltns(List<PractitionerHcProvORGReltn> practitionerHcProvORGReltns) {
        this.practitionerHcProvORGReltns = practitionerHcProvORGReltns;
    }

    public void addPractitionerHcProvORGReltn(PractitionerHcProvORGReltn practitionerHcProvORGReltn) {
        practitionerHcProvORGReltns.add(practitionerHcProvORGReltn);
        practitionerHcProvORGReltn.setHealthcareProviderOrganization(this);
    }





    public String getName() {
        return providerName;
    }

    public void setName(String name) {
        this.providerName = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
