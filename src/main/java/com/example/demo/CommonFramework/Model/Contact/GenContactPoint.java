package com.example.demo.CommonFramework.Model.Contact;

import com.example.demo.CommonFramework.Model.Facility.GenFacilityContactPoint;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GEN_CONTACT_POINT")
public class GenContactPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_POINT_ID")
    private Long contactPointId;
    @Column(name = "NAME", length = 20, nullable = true)
    private String name;
    @Column(name = "COUNTRY_CODE", length = 5, nullable = true)
    private String countryCode;
    @Column(name = "AREA_CODE", length = 5, nullable = true)
    private String areaCode;
    @Column(name = "CONTACT_NUMBER", length = 20, nullable = true)
    private String contactNumber;
    @Column(name = "OTHER_CONTACT_VALUE", length = 40, nullable = true)
    private String otherContactValue;
    @Column(name = "CONTACT_POINT_TYPE_CD", nullable = true)
    private int contactPointTypeCd;
    @Column(name = "RANK", nullable = true)
    private int rank;

    @OneToMany(mappedBy = "contactPoint")
    private List<GenPartyContactPoint> genContact=new ArrayList<>();

    @OneToMany(mappedBy = "genContactPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenFacilityContactPoint> facilityContactPoints = new ArrayList<>();

    public void addFacilityContactPoint(GenFacilityContactPoint facilityContactPoint) {
        facilityContactPoints.add(facilityContactPoint);
        facilityContactPoint.setGenContactPoint(this);
    }

    public GenContactPoint() {
    }


    public GenContactPoint(String countryCode, String contactNumber, int contactPointTypeCd) {
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
        this.contactPointTypeCd = contactPointTypeCd;
    }

    public GenContactPoint( String contactNumber, int contactPointTypeCd) {
        this.contactNumber = contactNumber;
        this.contactPointTypeCd = contactPointTypeCd;
        this.genContact=new ArrayList<>();
    }

    public Long getContactPointId() {
        return contactPointId;
    }

    public void setContactPointId(Long contactPointId) {
        this.contactPointId = contactPointId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getContactPointTypeCd() {
        return contactPointTypeCd;
    }

    public void setContactPointTypeCd(int contactPointTypeCd) {
        this.contactPointTypeCd = contactPointTypeCd;
    }
}
