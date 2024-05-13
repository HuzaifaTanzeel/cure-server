package com.example.demo.CommonFramework.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;

public class FacilityRequest {

    @JsonProperty("partyId")
    private Long partyId;

    @JsonProperty("partyRoleTypeId")
    private Long partyRoleTypeId;

    @JsonProperty("orgId")
    private Long orgId;

    @JsonProperty("facilityId")
    private Long facilityId;

    @JsonProperty("facilityName")
    private String facilityName;

    @JsonProperty("addressId")
    private Long addressId;

    @JsonProperty("addrTypeCd")
    private int addrTypeCd;

    @JsonProperty("addrLine1")
    private String addrLine1;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("contactNumber")
    private String contactNumber;

    @JsonProperty("contactPointTypeCd")
    private int contactPointTypeCd;

    @JsonProperty("facilityTypeCd")
    private int facilityTypeCd;

    @JsonProperty("facilityPhyTypeCd")
    private int facilityPhyTypeCd;

    @JsonProperty("facilityStatusCd")
    private int facilityStatusCd;

    @JsonProperty("facilityOperationalStatusCd")
    private int facilityOperationalStatusCd;

    public FacilityRequest() {
        // Default constructor
    }

    public FacilityRequest(Long partyId, Long partyRoleTypeId, Long orgId, Long facilityId, String facilityName,
                           Long addressId, int addrTypeCd, String addrLine1,
                           String countryCode, String contactNumber, int contactPointTypeCd,
                           int facilityTypeCd, int facilityPhyTypeCd, int facilityStatusCd, int facilityOperationalStatusCd) {
        this.partyId = partyId;
        this.partyRoleTypeId = partyRoleTypeId;
        this.orgId = orgId;
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.addressId = addressId;
        this.addrTypeCd = addrTypeCd;
        this.addrLine1 = addrLine1;
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
        this.contactPointTypeCd = contactPointTypeCd;
        this.facilityTypeCd = facilityTypeCd;
        this.facilityPhyTypeCd = facilityPhyTypeCd;
        this.facilityStatusCd = facilityStatusCd;
        this.facilityOperationalStatusCd = facilityOperationalStatusCd;
    }

    // Getters and setters
}
