package com.example.demo.CommonFramework.Model.Contact;

import com.example.demo.CommonFramework.Model.AdmPractitioner;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import jakarta.persistence.*;

@Entity
@Table(name = "GEN_PARTY_CONTACTPOINT")
public class GenPartyContactPoint {

    @EmbeddedId
    private GenPartyContactPointId genPartyContactPointId;


    @ManyToOne
    @JoinColumn(name = "CONTACT_POINT_ID", insertable = false, updatable = false)
    private GenContactPoint contactPoint;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("genPartyRole")
    @JoinColumns({
            @JoinColumn(name = "PARTY_ID", referencedColumnName = "PARTY_ID"),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID")
    })
    private GenPartyRole genPartyRole;

    public void setGenPartyRole(GenPartyRole genPartyRole) {
        this.genPartyRole = genPartyRole;
    }


    public GenPartyContactPoint(GenPartyContactPointId genPartyContactPointId) {
        this.genPartyContactPointId = genPartyContactPointId;
    }



    public GenPartyContactPoint(AdmPractitioner admPractitioner, GenContactPoint genContactPoint){

        this.contactPoint=genContactPoint;
        this.genPartyContactPointId=
                new GenPartyContactPointId(
                        admPractitioner.getGenPartyRoleId(),
                        genContactPoint.getContactPointId()
                );
    }

    public GenPartyContactPoint() {
    }

    public GenPartyContactPointId getGenPartyContactPointId() {
        return genPartyContactPointId;
    }

    public void setGenPartyContactPointId(GenPartyContactPointId genPartyContactPointId) {
        this.genPartyContactPointId = genPartyContactPointId;
    }

    public GenContactPoint getContactPoint() {
        return contactPoint;
    }


    public void setContactPoint(GenContactPoint contactPoint) {
        this.contactPoint = contactPoint;
    }


}
