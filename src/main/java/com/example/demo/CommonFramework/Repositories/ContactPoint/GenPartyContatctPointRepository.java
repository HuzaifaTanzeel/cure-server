package com.example.demo.CommonFramework.Repositories.ContactPoint;

import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPointId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenPartyContatctPointRepository extends JpaRepository<GenPartyContactPoint, GenPartyContactPointId> {
    List<GenPartyContactPoint> findByGenPartyContactPointId_GenPartyRole_PartyIdAndGenPartyContactPointId_GenPartyRole_PartyRoleTypeId(
            Long partyId, Long partyRoleTypeId);

}
