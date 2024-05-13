package com.example.demo.CommonFramework.Model.Facility;
import com.example.demo.CommonFramework.Model.Contact.GenContactPoint;

import jakarta.persistence.*;

@Entity
@Table(name = "GEN_FACILITY_CONTACT_POINT")
public class GenFacilityContactPoint {

    @EmbeddedId
    private GenFacilityContactPointId id;

    @ManyToOne
    @MapsId("facilityId")
    @JoinColumn(name = "FACILITY_ID", referencedColumnName = "FACILITY_ID")
    private GenFacility genFacility;

    @ManyToOne
    @MapsId("contactPointId")
    @JoinColumn(name = "CONTACT_POINT_ID", referencedColumnName = "CONTACT_POINT_ID")
    private GenContactPoint genContactPoint;

    // Constructors, getters, setters, etc.

    public GenFacilityContactPoint() {
        this.id = new GenFacilityContactPointId();
    }

    public GenFacilityContactPoint(GenFacility genFacility, GenContactPoint genContactPoint) {

        this.id = new GenFacilityContactPointId(genFacility.getFacilityId(), genContactPoint.getContactPointId());
        this.genFacility = genFacility;
        this.genContactPoint = genContactPoint;
    }

    public GenFacilityContactPointId getId() {
        return id;
    }

    public void setId(GenFacilityContactPointId id) {
        this.id = id;
    }

    public GenFacility getGenFacility() {
        return genFacility;
    }

    public void setGenFacility(GenFacility genFacility) {
        this.genFacility = genFacility;
    }

    public GenContactPoint getGenContactPoint() {
        return genContactPoint;
    }

    public void setGenContactPoint(GenContactPoint genContactPoint) {
        this.genContactPoint = genContactPoint;
    }
}

