package com.example.demo.CommonFramework.Model.Identity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GEN_IDENTITY_TYPE")
public class GenIdentityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDENTITY_TYPE_ID")
    private Long identityTypeId;

    @OneToMany(mappedBy = "genIdentityType")
    private List<GenPartyIdentity> genPartyIdentity=new ArrayList<>();

    @Column(name = "IDENTITY_NAME")
    private String identityName;

    @Column(name = "DESCRIPTION")
    private String description;

    public GenIdentityType() {
    }

    public Long getIdentityTypeId() {
        return identityTypeId;
    }

    public void setIdentityTypeId(Long identityTypeId) {
        this.identityTypeId = identityTypeId;
    }

    public List<GenPartyIdentity> getGenPartyIdentity() {
        return genPartyIdentity;
    }

    public void setGenPartyIdentity(List<GenPartyIdentity> genPartyIdentity) {
        this.genPartyIdentity = genPartyIdentity;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }
}
