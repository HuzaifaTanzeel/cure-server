package com.example.demo.CommonFramework.Model.PartyRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenPartyRoleId implements Serializable {


    @Column(name = "PARTY_ID")
    private Long partyId;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    public GenPartyRoleId(Long partyId, Long partyRoleTypeId) {
        this.partyId = partyId;
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(Long partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public GenPartyRoleId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenPartyRoleId that = (GenPartyRoleId) o;
        return partyRoleTypeId == that.partyRoleTypeId && Objects.equals(partyId, that.partyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partyId, partyRoleTypeId);
    }
}
