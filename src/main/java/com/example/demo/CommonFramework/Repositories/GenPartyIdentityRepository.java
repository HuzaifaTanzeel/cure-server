package com.example.demo.CommonFramework.Repositories;

import com.example.demo.CommonFramework.Model.Identity.GenPartyIdentity;
import com.example.demo.CommonFramework.Model.Identity.GenPartyIdentityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenPartyIdentityRepository extends JpaRepository<GenPartyIdentity, GenPartyIdentityId> {

    List<GenPartyIdentity> findByGenPartyIdentityId_GenPartyRole_PartyIdAndGenPartyIdentityId_GenPartyRole_PartyRoleTypeId(Long partyId, Long partyRoleTypeId);
}
