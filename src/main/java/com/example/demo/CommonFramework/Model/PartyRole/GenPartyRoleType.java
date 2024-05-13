package com.example.demo.CommonFramework.Model.PartyRole;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(
        name = "GEN_PARTY_ROLE_TYPE"
)
public class GenPartyRoleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "PARTY_TYPE_CD")
    private int partyTypeCd;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "partyRoleTypeId")
    private List<GenPartyRole> partyRoles;

    public GenPartyRoleType(String roleName, int partyTypeCd) {
        this.roleName = roleName;
        this.partyTypeCd = partyTypeCd;
    }

    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(Long partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getPartyTypeCd() {
        return partyTypeCd;
    }

    public void setPartyTypeCd(int partyTypeCd) {
        this.partyTypeCd = partyTypeCd;
    }

    public GenPartyRoleType() {
    }
}
