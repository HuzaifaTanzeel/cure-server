package com.example.demo.CommonFramework.Model.Identity;

import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "GEN_PARTY_IDENTITY")
public class GenPartyIdentity {

    @EmbeddedId
    private GenPartyIdentityId genPartyIdentityId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDENTITY_TYPE_ID", insertable = false, updatable = false)
    private GenIdentityType genIdentityType;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @MapsId("genPartyRole")
    @JoinColumns({
            @JoinColumn(name = "PARTY_ID", referencedColumnName = "PARTY_ID"),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID")
    })
    private GenPartyRole genPartyRole;

    @Column(name = "VALUE")
    private String Value;

    @Column(name = "VALID_FROM_DT")
    private Date validFromDt;

    @Column(name = "VALID_TO_DT")
    private Date validToDt;

    public void setGenPartyRole(GenPartyRole genPartyRole) {
        this.genPartyRole = genPartyRole;
    }


    public GenPartyIdentity() {
    }

    public GenPartyIdentity(GenPartyIdentityId genPartyIdentityId) {
        this.genPartyIdentityId=genPartyIdentityId;
    }

    public GenPartyIdentity(GenPartyIdentityId genPartyIdentityId, String value, Date validFromDt, Date validToDt) {
        this.genPartyIdentityId = genPartyIdentityId;
        Value = value;
        this.validFromDt = validFromDt;
        this.validToDt = validToDt;
    }

    public GenPartyIdentityId getGenPartyIdentityId() {
        return genPartyIdentityId;
    }

    public void setGenPartyIdentityId(GenPartyIdentityId genPartyIdentityId) {
        this.genPartyIdentityId = genPartyIdentityId;
    }

    public GenIdentityType getGenIdentityType() {
        return genIdentityType;
    }

    public void setGenIdentityType(GenIdentityType genIdentityType) {
        this.genIdentityType = genIdentityType;
    }

    public GenPartyRole getGenPartyRole() {
        return genPartyRole;
    }


    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
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
