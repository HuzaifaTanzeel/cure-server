package com.example.demo.CommonFramework.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactPointDTO {

    @JsonProperty("partyId")
    private Long partyId;
    @JsonProperty("partyRoleTypeId")
    private Long partyRoleTypeId;

    @JsonIgnore
    private Long contactPointId;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("contactNumber")
    private String contactNumber;


    @JsonProperty("contactPointTypeCd")
    private int contactPointTypeCd;

    @JsonProperty(("contactPointId"))
    public Long getContactPointId() {
        return contactPointId;
    }


    // Constructors, getters, setters

    public ContactPointDTO() {
        // Default constructor
    }

    public ContactPointDTO(Long partyId,Long partyRoleTypeId,Long contactPointId, String countryCode, String contactNumber,  int contactPointTypeCd) {
        this.partyId=partyId;
        this.partyRoleTypeId=partyRoleTypeId;
        this.contactPointId = contactPointId;
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
        this.contactPointTypeCd = contactPointTypeCd;

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

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(Long partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    // Add getters and setters as needed
}

