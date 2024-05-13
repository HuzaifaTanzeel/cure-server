package com.example.demo.CommonFramework.Model.Identity;

import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenPartyIdentityId implements Serializable {

    @Embedded
    private GenPartyRoleId genPartyRole;


    @Column(name = "IDENTITY_TYPE_ID")
    private Long identityTypeId;

    public GenPartyIdentityId() {

    }

    public GenPartyIdentityId(GenPartyRoleId genPartyRole, Long identityTypeId) {
        this.genPartyRole = genPartyRole;
        this.identityTypeId = identityTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenPartyIdentityId that = (GenPartyIdentityId) o;
        return Objects.equals(genPartyRole, that.genPartyRole) && Objects.equals(identityTypeId, that.identityTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genPartyRole, identityTypeId);
    }

    public GenPartyRoleId getGenPartyRole() {
        return genPartyRole;
    }

    public Long getIdentityTypeId() {
        return identityTypeId;
    }
}
