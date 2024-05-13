package com.example.demo.CommonFramework.Repositories.Address;

import com.example.demo.CommonFramework.Model.Address.GenPartyAddress;
import com.example.demo.CommonFramework.Model.Address.GenPartyAddressId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenPartyAddressRepository extends JpaRepository<GenPartyAddress, GenPartyAddressId> {
    List<GenPartyAddress> findByGenPartyAddressId_GenPartyRole_PartyIdAndGenPartyAddressId_GenPartyRole_PartyRoleTypeId(
            Long partyId, Long partyRoleTypeId);
    //Optional<GenPartyAddress> findBy()

}
