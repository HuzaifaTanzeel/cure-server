package com.example.demo.CommonFramework.Model;

import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccountProfile;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
        name = "GEN_PARTY"
)
public class GenParty {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "PARTY_ID"
    )
    private Long partyId;
    @Column(
            name = "PARTY_TYPE_CD"
    )
    private int partyTypeCd;

    @OneToMany(mappedBy = "partyId")
    private List<GenPartyRole> partyRoles;

    @OneToMany(mappedBy = "genParty")
    private List<UAMUserAccountProfile> accountProfiles;

    @OneToMany(mappedBy = "genParty")
    private List<GenPartyQualification> genPartyQualifications=new ArrayList<>();

    public GenParty() {
    }



    public GenParty(int partyTypeCd) {
        this.partyTypeCd = partyTypeCd;
    }

    public void setPartyRoles(List<GenPartyRole> partyRoles) {
        this.partyRoles = partyRoles;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPartyId() {
        return this.partyId;
    }

    public GenParty getParty(){
        return this;
    }



    public int getPartyTypeCd() {
        return this.partyTypeCd;
    }

    public void setPartyTypeCd(int partyTypeCd) {
        this.partyTypeCd = partyTypeCd;
    }

    public void addAccountProfile(UAMUserAccountProfile userAccountProfile) {
        if (accountProfiles == null) {
            accountProfiles = new ArrayList<>();
        }
        accountProfiles.add(userAccountProfile);
        userAccountProfile.setGenParty(this);
    }

    public void addQualification(GenPartyQualification genPartyQualification) {
        this.genPartyQualifications.add(genPartyQualification);
    }
}
