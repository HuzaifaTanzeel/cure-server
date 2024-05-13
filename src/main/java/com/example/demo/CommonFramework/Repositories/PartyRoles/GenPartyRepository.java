package com.example.demo.CommonFramework.Repositories.PartyRoles;

import com.example.demo.CommonFramework.Model.GenParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenPartyRepository extends JpaRepository<GenParty,Long> {

    //Page<GenParty> findTopByOrderByPartyIdDesc(Pageable pageable);

    //@Query("SELECT MAX(partyId) FROM GEN_PARTY")
    //Integer findMaxPartyId();
}
