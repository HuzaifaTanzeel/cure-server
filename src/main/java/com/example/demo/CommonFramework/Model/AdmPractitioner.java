package com.example.demo.CommonFramework.Model;


import com.example.demo.CommonFramework.Model.Facility.PractitionerFacilityAffiliation;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleType;
import com.example.demo.CommonFramework.Model.Practice.PractitionerHcProvORGReltn;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity

@Table(
        name = "PRACTITIONER"
)
//@PrimaryKeyJoinColumn(
//        name = "PRACTITIONER_PID"
//)
public class AdmPractitioner extends GenPartyRole {


    private String cureId;


//    public AdmPractitioner(String firstName, String lastName) {
//        //super(firstName, lastName);
//        this.cureId = generateCureId();
//    }

    @OneToMany(mappedBy = "admPractitioner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PractitionerHcProvORGReltn> practitionerHcProvORGReltns = new ArrayList<>();

    public void addPractitionerHcProvORGReltn(PractitionerHcProvORGReltn practitionerHcProvORGReltn) {
        practitionerHcProvORGReltns.add(practitionerHcProvORGReltn);
        practitionerHcProvORGReltn.setAdmPractitioner(this);
    }

    public void removePractitionerHcProvORGReltn(PractitionerHcProvORGReltn practitionerHcProvORGReltn) {
        practitionerHcProvORGReltns.remove(practitionerHcProvORGReltn);
        practitionerHcProvORGReltn.setAdmPractitioner(null);
    }


    @OneToMany(mappedBy = "admPractitioner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PractitionerFacilityAffiliation> practitionerFacilityAffiliations = new ArrayList<>();

    public void addPractitionerFacilityAffiliation(PractitionerFacilityAffiliation practitionerFacilityAffiliation) {
        practitionerFacilityAffiliations.add(practitionerFacilityAffiliation);
        practitionerFacilityAffiliation.setAdmPractitioner(this);
    }
    public AdmPractitioner() {

    }

    public void printdata(){
        System.out.println(
                this.getGenPartyRoleId() +"   "+ this.getPartyRoleTypeId() + "  " + this.getPartyId() +" "+ this.getGenPartyRole()
        );
    }

    public AdmPractitioner(GenPartyRoleId genPartyRoleId, GenParty partyId, GenPartyRoleType partyRoleTypeId) {
        super(genPartyRoleId, partyId, partyRoleTypeId);
        this.cureId = generateCureId();
    }



    public AdmPractitioner(GenPerson genPerson, GenPartyRoleType partyRoleTypeId) {
        super(genPerson, partyRoleTypeId);
    }


    public List<PractitionerHcProvORGReltn> getPractitionerHcProvORGReltns() {
        return practitionerHcProvORGReltns;
    }

    public void setPractitionerHcProvORGReltns(List<PractitionerHcProvORGReltn> practitionerHcProvORGReltns) {
        this.practitionerHcProvORGReltns = practitionerHcProvORGReltns;
    }

    private String generateCureId() {
        String randomUUID = UUID.randomUUID().toString();
        return "cure_" + randomUUID;
    }
}
