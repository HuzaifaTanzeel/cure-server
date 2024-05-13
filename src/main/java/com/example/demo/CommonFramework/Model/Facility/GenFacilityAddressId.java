package com.example.demo.CommonFramework.Model.Facility;



import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GenFacilityAddressId implements Serializable {

    @Column(name = "FACILITY_ID")
    private Long facilityId;

    @Column(name = "ADDRESS_ID")
    private Long addressId;

    // Constructors, getters, and setters

    // Implement equals and hashCode methods

    // Constructors, getters, setters, etc.

    public GenFacilityAddressId() {
    }

    public GenFacilityAddressId(Long facilityId, Long addressId) {
        this.facilityId = facilityId;
        this.addressId = addressId;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenFacilityAddressId that = (GenFacilityAddressId) o;
        return Objects.equals(facilityId, that.facilityId) && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facilityId, addressId);
    }
}

