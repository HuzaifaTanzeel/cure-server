package com.example.demo.CommonFramework.Model.Facility;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PractitionerFacilityAffiliationId implements Serializable {

    @Column(name = "FACILITY_ID")
    private Long facilityId;

    @Column(name = "PRACTITIONER_PID")
    private Long practitionerPid;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "FACILITY_AFFILIATION_ROLE_CD")
    private int facilityAffiliationRoleCd;

    // Constructors, getters, and setters

    // Make sure to implement equals and hashCode methods


    public PractitionerFacilityAffiliationId(Long facilityId, Long practitionerPid, Long partyRoleTypeId, int facilityAffiliationRoleCd) {
        this.facilityId = facilityId;
        this.practitionerPid = practitionerPid;
        this.partyRoleTypeId = partyRoleTypeId;
        this.facilityAffiliationRoleCd = facilityAffiliationRoleCd;
    }

    public PractitionerFacilityAffiliationId() {
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getPractitionerPid() {
        return practitionerPid;
    }

    public void setPractitionerPid(Long practitionerPid) {
        this.practitionerPid = practitionerPid;
    }

    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(Long partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public int getFacilityAffiliationRoleCd() {
        return facilityAffiliationRoleCd;
    }

    public void setFacilityAffiliationRoleCd(int facilityAffiliationRoleCd) {
        this.facilityAffiliationRoleCd = facilityAffiliationRoleCd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PractitionerFacilityAffiliationId that = (PractitionerFacilityAffiliationId) o;
        return facilityAffiliationRoleCd == that.facilityAffiliationRoleCd && Objects.equals(facilityId, that.facilityId) && Objects.equals(practitionerPid, that.practitionerPid) && Objects.equals(partyRoleTypeId, that.partyRoleTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facilityId, practitionerPid, partyRoleTypeId, facilityAffiliationRoleCd);
    }
}

