package com.example.demo.CommonFramework.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FacilityDTO {


    private Long partyId;
    private Long partyRoleTypeId;
    private Long orgId;

    @JsonIgnore
    private Long facilityId;
    private String facilityName;

    @JsonIgnore
    private Long addressId;

    private int addrTypeCd;
    private String addrLine1;

    private String countryCode;
    private String contactNumber;
    private int contactPointTypeCd;

    private int facilityTypeCd;

    private int facilityPhyTypeCd;

    private int facilityStatusCd;

    private int facilityOperationalStatusCd;

    private int facilityAffiliationRoleCd;

    @JsonIgnore
    private Long resourceId;

    public FacilityDTO(Long partyId,Long partyRoleTypeId,Long orgId, Long facilityId, String facilityName,Long addressId, int addrTypeCd, String addrLine1, String countryCode, String contactNumber, int contactPointTypeCd, int facilityTypeCd, int facilityPhyTypeCd, int facilityStatusCd, int facilityOperationalStatusCd,int facilityAffiliationRoleCd, Long resourceId) {
        this.partyId=partyId;
        this.partyRoleTypeId=partyRoleTypeId;
        this.orgId = orgId;
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.addressId=addressId;
        this.addrTypeCd = addrTypeCd;
        this.addrLine1 = addrLine1;
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
        this.contactPointTypeCd = contactPointTypeCd;
        this.facilityTypeCd = facilityTypeCd;
        this.facilityPhyTypeCd = facilityPhyTypeCd;
        this.facilityStatusCd = facilityStatusCd;
        this.facilityOperationalStatusCd = facilityOperationalStatusCd;
        this.facilityAffiliationRoleCd=facilityAffiliationRoleCd;
        this.resourceId=resourceId;
    }

    public int getFacilityAffiliationRoleCd() {
        return facilityAffiliationRoleCd;
    }

    public Long getPartyId() {
        return partyId;
    }

    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public Long getOrgId() {
        return orgId;
    }

    @JsonProperty("facilityId")
    public Long getFacilityId() {
        return facilityId;
    }

    @JsonProperty("addressId")
    public Long getAddressId() {
        return addressId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public int getAddrTypeCd() {
        return addrTypeCd;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getContactPointTypeCd() {
        return contactPointTypeCd;
    }

    public int getFacilityTypeCd() {
        return facilityTypeCd;
    }

    public int getFacilityPhyTypeCd() {
        return facilityPhyTypeCd;
    }

    public int getFacilityStatusCd() {
        return facilityStatusCd;
    }

    public int getFacilityOperationalStatusCd() {
        return facilityOperationalStatusCd;
    }

    @JsonProperty("resourceId")
    public Long getResourceId() {
        return resourceId;
    }
}
