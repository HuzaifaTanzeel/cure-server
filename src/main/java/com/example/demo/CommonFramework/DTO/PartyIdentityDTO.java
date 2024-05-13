package com.example.demo.CommonFramework.DTO;

import java.util.Date;

public class PartyIdentityDTO {

    private Long partyId;
    private Long partyRoleTypeId;
    private Long identityTypeId;
    private String identityTypeName;
    private String value;
    private Date validFromDt;
    private Date validToDt;

    public PartyIdentityDTO() {
    }

    public PartyIdentityDTO(Long partyId, Long partyRoleTypeId, Long identityTypeId, String identityTypeName, String value, Date validFromDt, Date validToDt) {
        this.partyId = partyId;
        this.partyRoleTypeId = partyRoleTypeId;
        this.identityTypeId = identityTypeId;
        this.identityTypeName = identityTypeName;
        this.value = value;
        this.validFromDt = validFromDt;
        this.validToDt = validToDt;
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

    public Long getIdentityTypeId() {
        return identityTypeId;
    }

    public void setIdentityTypeId(Long identityTypeId) {
        this.identityTypeId = identityTypeId;
    }

    public String getIdentityTypeName() {
        return identityTypeName;
    }

    public void setIdentityTypeName(String identityTypeName) {
        this.identityTypeName = identityTypeName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getValidFromDt() {
        return validFromDt;
    }

    public void setValidFromDt(Date validFromDt) {
        this.validFromDt = validFromDt;
    }

    public Date getValidToDt() {
        return validToDt;
    }

    public void setValidToDt(Date validToDt) {
        this.validToDt = validToDt;
    }
}
