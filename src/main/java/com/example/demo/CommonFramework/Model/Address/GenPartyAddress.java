package com.example.demo.CommonFramework.Model.Address;


import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import jakarta.persistence.*;

@Entity
@Table(name = "GEN_PARTY_ADDRESS")
public class GenPartyAddress {

    @EmbeddedId
    private GenPartyAddressId genPartyAddressId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDRESS_ID", insertable = false, updatable = false)
    private GenAddress address;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @MapsId("genPartyRole")
    @JoinColumns({
            @JoinColumn(name = "PARTY_ID", referencedColumnName = "PARTY_ID"),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID")
    })
    private GenPartyRole genPartyRole;

    public void setGenPartyRole(GenPartyRole genPartyRole) {
        this.genPartyRole = genPartyRole;
    }

    public GenPartyAddress() {
    }

    public GenPartyAddress(GenPartyAddressId genPartyAddressId) {
        this.genPartyAddressId = genPartyAddressId;
    }

    public GenPartyAddressId getGenPartyAddressId() {
        return genPartyAddressId;
    }

    public void setGenPartyAddressId(GenPartyAddressId genPartyAddressId) {
        this.genPartyAddressId = genPartyAddressId;
    }

    public GenAddress getAddress() {
        return address;
    }

    public void setAddress(GenAddress address) {
        this.address = address;
    }

    public GenPartyRole getGenPartyRole() {
        return genPartyRole;
    }
}
