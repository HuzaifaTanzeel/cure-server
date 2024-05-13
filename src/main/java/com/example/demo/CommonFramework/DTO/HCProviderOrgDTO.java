package com.example.demo.CommonFramework.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HCProviderOrgDTO {

    private Long partyId;
    private Long partyRoleTypeId;

    @JsonIgnore
    private Long hcProvOrgId;
    private int partyTypeCd;
    private String providerName;
    private int orgTypeCd;
    private int practiceRoleCd;

    // Constructors, getters, and setters

    public HCProviderOrgDTO() {
    }

    public HCProviderOrgDTO(Long partyId, Long partyRoleTypeId,Long hcProvOrgId, int partyTypeCd, String providerName, int orgTypeCd,int practiceRoleCd) {
        this.partyId = partyId;
        this.partyRoleTypeId = partyRoleTypeId;
        this.hcProvOrgId=hcProvOrgId;
        this.partyTypeCd = partyTypeCd;
        this.providerName = providerName;
        this.orgTypeCd = orgTypeCd;
        this.practiceRoleCd=practiceRoleCd;
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

    public int getPartyTypeCd() {
        return partyTypeCd;
    }

    public void setPartyTypeCd(int partyTypeCd) {
        this.partyTypeCd = partyTypeCd;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @JsonProperty("hcProvOrgId")
    public Long getHcProvOrgId() {
        return hcProvOrgId;
    }

    public void setHcProvOrgId(Long hcProvOrgId) {
        this.hcProvOrgId = hcProvOrgId;
    }

    public int getOrgTypeCd() {
        return orgTypeCd;
    }

    public int getPracticeRoleCd() {
        return practiceRoleCd;
    }

    public void setPracticeRoleCd(int practiceRoleCd) {
        this.practiceRoleCd = practiceRoleCd;
    }

    public void setOrgTypeCd(int orgTypeCd) {
        this.orgTypeCd = orgTypeCd;
    }
}

