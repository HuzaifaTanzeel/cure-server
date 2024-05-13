package com.example.demo.CommonFramework.Model.Address;

import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenPartyAddressId implements Serializable {

    @Embedded
    private GenPartyRoleId genPartyRole;

    @Column(name = "ADDRESS_ID")
    private Long AddressId;

    public GenPartyAddressId() {

    }

    public GenPartyAddressId(GenPartyRoleId genPartyRole, Long AddressId) {
        this.genPartyRole = genPartyRole;
//        this.genPartyRole= new GenPartyRoleId(
//                genPartyRole.getPartyId(),
//                genPartyRole.getPartyRoleTypeId()
//        );
        this.AddressId = AddressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenPartyAddressId that = (GenPartyAddressId) o;
        return AddressId == that.AddressId && Objects.equals(genPartyRole, that.genPartyRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genPartyRole, AddressId);
    }


    public GenPartyRoleId getGenPartyRole() {
        return genPartyRole;
    }

    public Long getAddressId() {
        return AddressId;
    }
}
