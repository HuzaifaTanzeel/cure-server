package com.example.demo.CommonFramework.Model.Facility;

import com.example.demo.CommonFramework.Model.Practice.GenOrganization;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GEN_FACILITY")
public class GenFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACILITY_ID")
    private Long facilityId;

    @Column(name = "NAME", length = 40, nullable = false)
    private String name;

    @Column(name = "ALIAS", length = 40)
    private String alias;

    @Column(name = "DESCRIPTION", length = 256)
    private String description;

    @Column(name = "ORG_ID")
    private Long orgId;
    @ManyToOne
    @JoinColumn(name = "ORG_ID", referencedColumnName = "PARTY_ID", nullable = false,insertable = false,updatable = false)
    private GenOrganization organization;

//    @ManyToOne
//    @JoinColumn(name = "PartOf", referencedColumnName = "FACILITY_ID")
//    private GenFacility partOf;

    @Column(name = "FACILITY_TYPE_CD", nullable = false)
    private int facilityTypeCd;

    @Column(name = "FACILITY_PHY_TYPE_CD")
    private int facilityPhyTypeCd;

    @Column(name = "FACILITY_STATUS_CD")
    private int facilityStatusCd;

    @Column(name = "FACILITY_OPERATIONAL_STATUS_CD")
    private int facilityOperationalStatusCd;

    @OneToMany(mappedBy = "genFacility",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PractitionerFacilityAffiliation> practitionerFacilityAffiliations=new ArrayList<>();

    public void addPractitionerFacilityAffiliation(PractitionerFacilityAffiliation practitionerFacilityAffiliation){
        practitionerFacilityAffiliations.add(practitionerFacilityAffiliation);
        practitionerFacilityAffiliation.setGenFacility(this);
    }


    @OneToMany(mappedBy = "genFacility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenFacilityAddress> facilityAddresses=new ArrayList<>();

    // Other methods, if needed

    public void addFacilityAddress(GenFacilityAddress facilityAddress) {
        facilityAddresses.add(facilityAddress);
        facilityAddress.setGenFacility(this);
    }
    // Constructors, getters, setters, etc.


    @OneToMany(mappedBy = "genFacility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenFacilityContactPoint> facilityContactPoints = new ArrayList<>();

    // Other methods, if needed

    public void addFacilityContactPoint(GenFacilityContactPoint facilityContactPoint) {
        facilityContactPoints.add(facilityContactPoint);
        facilityContactPoint.setGenFacility(this);
    }


    public GenFacility() {
    }

    public GenFacility(String name, int facilityTypeCd, int facilityPhyTypeCd, int facilityStatusCd, int facilityOperationalStatusCd,Long orgId) {
        this.name = name;
        this.facilityTypeCd = facilityTypeCd;
        this.facilityPhyTypeCd = facilityPhyTypeCd;
        this.facilityStatusCd = facilityStatusCd;
        this.facilityOperationalStatusCd = facilityOperationalStatusCd;
        this.orgId=orgId;
    }

    public GenFacility(String name, String alias, String description, GenOrganization organization, GenFacility partOf, int facilityTypeCd, int facilityPhyTypeCd, int facilityStatusCd, int facilityOperationalStatusCd) {
        this.name = name;
        this.alias = alias;
        this.description = description;
        this.organization = organization;
        //this.partOf = partOf;
        this.facilityTypeCd = facilityTypeCd;
        this.facilityPhyTypeCd = facilityPhyTypeCd;
        this.facilityStatusCd = facilityStatusCd;
        this.facilityOperationalStatusCd = facilityOperationalStatusCd;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public GenOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(GenOrganization organization) {
        this.organization = organization;
    }

//    public GenFacility getPartOf() {
//        return partOf;
//    }
//
//    public void setPartOf(GenFacility partOf) {
//        this.partOf = partOf;
//    }

    public int getFacilityTypeCd() {
        return facilityTypeCd;
    }

    public void setFacilityTypeCd(int facilityTypeCd) {
        this.facilityTypeCd = facilityTypeCd;
    }

    public int getFacilityPhyTypeCd() {
        return facilityPhyTypeCd;
    }

    public void setFacilityPhyTypeCd(int facilityPhyTypeCd) {
        this.facilityPhyTypeCd = facilityPhyTypeCd;
    }

    public int getFacilityStatusCd() {
        return facilityStatusCd;
    }

    public void setFacilityStatusCd(int facilityStatusCd) {
        this.facilityStatusCd = facilityStatusCd;
    }

    public int getFacilityOperationalStatusCd() {
        return facilityOperationalStatusCd;
    }

    public void setFacilityOperationalStatusCd(int facilityOperationalStatusCd) {
        this.facilityOperationalStatusCd = facilityOperationalStatusCd;
    }

    public List<PractitionerFacilityAffiliation> getPractitionerFacilityAffiliations() {
        return practitionerFacilityAffiliations;
    }

    public List<GenFacilityAddress> getFacilityAddresses() {
        return facilityAddresses;
    }

    public List<GenFacilityContactPoint> getFacilityContactPoints() {
        return facilityContactPoints;
    }
// Other methods, if needed
}

