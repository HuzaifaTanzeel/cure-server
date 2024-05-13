package com.example.demo.CommonFramework.Model.PartyRole;


import com.example.demo.CommonFramework.Model.Address.GenPartyAddress;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPoint;
import com.example.demo.CommonFramework.Model.GenParty;
import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Model.Identity.GenPartyIdentity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
        name = "GEN_PARTY_ROLE"
)
//@IdClass(GenPartyRoleId.class)
public class GenPartyRole {


    @EmbeddedId
    private GenPartyRoleId genPartyRoleId;
    //@Id
    @ManyToOne
    @MapsId("partyId")
    @JoinColumn(name = "PARTY_ID")
    private GenParty partyId;

    //@Id
    @ManyToOne
    @MapsId("partyRoleTypeId")
    @JoinColumn(name = "PARTY_ROLE_TYPE_ID")
    private GenPartyRoleType partyRoleTypeId;

    @OneToMany(mappedBy = "genPartyRole", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenPartyContactPoint> contactPoints = new ArrayList<>();

    @OneToMany(mappedBy = "genPartyRole", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<GenPartyAddress> genPartyAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "genPartyRole", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<GenPartyIdentity> genPartyIdentities = new ArrayList<>();


    //Practitioner Relationships with PractitionerHcProvORGReltn
//    @OneToMany(mappedBy = "admPractitioner", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
//    private List<PractitionerHcProvORGReltn> practitionerHcProvORGReltns=new ArrayList<>();

    public void addPartyContactPoint(GenPartyContactPoint contactPoint) {
        contactPoints.add(contactPoint);
        contactPoint.setGenPartyRole(this);
    }

    public void addPartyIdentity(GenPartyIdentity genPartyIdentity) {
        genPartyIdentities.add(genPartyIdentity);
        genPartyIdentity.setGenPartyRole(this);
    }

    public void addPartyAddress(GenPartyAddress PartyAddress) {
        genPartyAddresses.add(PartyAddress);
        PartyAddress.setGenPartyRole(this);
    }

    public GenPartyRoleId getGenPartyRoleId() {
        return genPartyRoleId;
    }

    public void setGenPartyRoleId(GenPartyRoleId genPartyRoleId) {
        this.genPartyRoleId = genPartyRoleId;
    }

    public GenParty getPartyId() {
        return partyId;
    }

    public void setPartyId(GenParty partyId) {
        this.partyId = partyId;
    }

    public GenPartyRoleType getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(GenPartyRoleType partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public GenPartyRole() {
    }

    public GenPartyRole getGenPartyRole(){
        return this;
    }

    public GenPartyRole(GenPerson genPerson, GenPartyRoleType partyRoleTypeId) {
        this.genPartyRoleId = new GenPartyRoleId(genPerson.getPartyId(), partyRoleTypeId.getPartyRoleTypeId());
        this.partyId = genPerson.getParty();
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public GenPartyRole(GenPartyRoleId genPartyRoleId, GenParty partyId, GenPartyRoleType partyRoleTypeId) {
        this.genPartyRoleId = genPartyRoleId;
        this.partyId = partyId;
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public List<GenPartyContactPoint> getContactPoints() {
        return contactPoints;
    }

    public List<GenPartyAddress> getGenPartyAddresses() {
        return genPartyAddresses;
    }

    //    public int hashCode() {
//        return Objects.hash(new Object[]{this., this.partyRoleTypeId});
//    }
//
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        } else if (obj != null && this.getClass() == obj.getClass()) {
//            GenPartyRole that = (GenPartyRole) obj;
//            return this.partyId == that.partyId && this.partyRoleTypeId == that.partyRoleTypeId;
//        } else {
//            return false;
//        }
//    }


    @Override
    public String toString() {
        return "GenPartyRole{" +
                "partyId=" + partyId +
                ", partyRoleTypeId=" + partyRoleTypeId +
                ", contactPoints=" + contactPoints +
                '}';
    }
}
