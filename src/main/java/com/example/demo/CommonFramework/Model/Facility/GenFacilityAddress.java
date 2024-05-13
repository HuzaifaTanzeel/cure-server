package com.example.demo.CommonFramework.Model.Facility;
import com.example.demo.CommonFramework.Model.Address.GenAddress;

import jakarta.persistence.*;

@Entity
@Table(name = "GEN_FACILITY_ADDRESS")
public class GenFacilityAddress {

    @EmbeddedId
    private GenFacilityAddressId id;

    @ManyToOne
    @MapsId("facilityId")
    @JoinColumn(name = "FACILITY_ID", referencedColumnName = "FACILITY_ID",insertable = false, updatable = false)
    private GenFacility genFacility;

    @ManyToOne
    @MapsId("addressId")
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID",insertable = false, updatable = false)
    private GenAddress genAddress;

    @Column(name = "RANK")
    private Byte rank;

    // Constructors, getters, setters, etc.

    public GenFacilityAddress() {
        this.id = new GenFacilityAddressId();
    }

    public GenFacilityAddress(GenFacility genFacility, GenAddress genAddress) {

        this.id = new GenFacilityAddressId(genFacility.getFacilityId(), genAddress.getAddressId());
        this.genFacility = genFacility;
        this.genAddress = genAddress;

    }

    public GenFacilityAddressId getId() {
        return id;
    }

    public void setId(GenFacilityAddressId id) {
        this.id = id;
    }

    public GenFacility getGenFacility() {
        return genFacility;
    }

    public void setGenFacility(GenFacility genFacility) {
        this.genFacility = genFacility;
    }

    public GenAddress getGenAddress() {
        return genAddress;
    }

    public void setGenAddress(GenAddress genAddress) {
        this.genAddress = genAddress;
    }

    public Byte getRank() {
        return rank;
    }

    public void setRank(Byte rank) {
        this.rank = rank;
    }
}
