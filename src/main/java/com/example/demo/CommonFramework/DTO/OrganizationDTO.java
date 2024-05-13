package com.example.demo.CommonFramework.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class OrganizationDTO {


    private Long partyId;
    private Long partyRoleTypeId;
    private int partyTypeCd;
    private String orgName;
    private int orgTypeCd;

    private MultipartFile imageData;
    // Constructors, getters, and setters

    public OrganizationDTO() {
    }

    public OrganizationDTO(Long partyId,int partyTypeCd, String orgName, int orgTypeCd,MultipartFile imageData) {
        this.partyId =partyId;
        this.partyTypeCd = partyTypeCd;
        this.orgName = orgName;
        this.orgTypeCd = orgTypeCd;
        this.imageData=imageData;
    }

    public int getPartyTypeCd() {
        return partyTypeCd;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    @JsonIgnore
    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public MultipartFile getImageData() {
        return imageData;
    }

    public void setImageData(MultipartFile imageData) {
        this.imageData = imageData;
    }

    public void setPartyRoleTypeId(Long partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public void setPartyTypeCd(int partyTypeCd) {
        this.partyTypeCd = partyTypeCd;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getOrgTypeCd() {
        return orgTypeCd;
    }

    public void setOrgTypeCd(int orgTypeCd) {
        this.orgTypeCd = orgTypeCd;
    }
}

