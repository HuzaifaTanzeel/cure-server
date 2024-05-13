package com.example.demo.CommonFramework.Model.Practice;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PractitionerHcProvORGReltnId implements Serializable {

    @Column(name = "HC_PROV_ORG_PID")
    private Long hcProvOrgPid;

    @Column(name = "PRACTITIONER_PID")
    private Long practitionerPid;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "PRACTICE_ROLE_CD")
    private int practiceRoleCd;

    // Constructors, getters, and setters

    // Make sure to implement equals and hashCode methods


    public PractitionerHcProvORGReltnId(Long hcProvOrgPid, Long practitionerPid, Long partyRoleTypeId, int practiceRoleCd) {
        this.hcProvOrgPid = hcProvOrgPid;
        this.practitionerPid = practitionerPid;
        this.partyRoleTypeId = partyRoleTypeId;
        this.practiceRoleCd = practiceRoleCd;
    }

    public PractitionerHcProvORGReltnId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PractitionerHcProvORGReltnId that = (PractitionerHcProvORGReltnId) o;
        return hcProvOrgPid == that.hcProvOrgPid && practitionerPid == that.practitionerPid && partyRoleTypeId == that.partyRoleTypeId && practiceRoleCd == that.practiceRoleCd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hcProvOrgPid, practitionerPid, partyRoleTypeId, practiceRoleCd);
    }

    public Long getHcProvOrgPid() {
        return hcProvOrgPid;
    }

    public void setHcProvOrgPid(Long hcProvOrgPid) {
        this.hcProvOrgPid = hcProvOrgPid;
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

    public int getPracticeRoleCd() {
        return practiceRoleCd;
    }

    public void setPracticeRoleCd(int practiceRoleCd) {
        this.practiceRoleCd = practiceRoleCd;
    }
}

