package com.example.demo.CommonFramework.Model.Facility;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GenFacilityContactPointId implements Serializable {

    @Column(name = "FACILITY_ID")
    private Long facilityId;

    @Column(name = "CONTACT_POINT_ID")
    private Long contactPointId;

    // Constructors, getters, setters

    // Make sure to implement equals and hashCode methods

    public GenFacilityContactPointId(Long facilityId, Long contactPointId) {
        this.facilityId = facilityId;
        this.contactPointId = contactPointId;
    }

    public GenFacilityContactPointId() {
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getContactPointId() {
        return contactPointId;
    }

    public void setContactPointId(Long contactPointId) {
        this.contactPointId = contactPointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenFacilityContactPointId that = (GenFacilityContactPointId) o;
        return Objects.equals(facilityId, that.facilityId) && Objects.equals(contactPointId, that.contactPointId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facilityId, contactPointId);
    }
}
