package com.example.demo.CommonFramework.Model.Contact;

import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GenPartyContactPointId implements Serializable {


    @Embedded
    private GenPartyRoleId genPartyRole;


    @Column(name = "CONTACT_POINT_ID")
    private Long contactPointId;

    public GenPartyContactPointId() {

    }

    public GenPartyContactPointId(GenPartyRoleId genPartyRole, Long contactPointId) {
        this.genPartyRole = genPartyRole;
//        this.genPartyRole= new GenPartyRoleId(
//                genPartyRole.getPartyId(),
//                genPartyRole.getPartyRoleTypeId()
//        );
        this.contactPointId = contactPointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenPartyContactPointId that = (GenPartyContactPointId) o;
        return Objects.equals(genPartyRole, that.genPartyRole) && Objects.equals(contactPointId, that.contactPointId);
    }

    public GenPartyRoleId getGenPartyRole() {
        return genPartyRole;
    }

    public void setGenPartyRole(GenPartyRoleId genPartyRole) {
        this.genPartyRole = genPartyRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genPartyRole, contactPointId);
    }

    public Long getContactPointId() {
        return contactPointId;
    }
}
