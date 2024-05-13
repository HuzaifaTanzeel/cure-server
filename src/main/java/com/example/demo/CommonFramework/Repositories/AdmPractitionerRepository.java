package com.example.demo.CommonFramework.Repositories;

import com.example.demo.CommonFramework.Model.AdmPractitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdmPractitionerRepository extends JpaRepository<AdmPractitioner, Long> {

    @Query("SELECT p FROM AdmPractitioner p WHERE p.genPartyRoleId.partyId = :partyId AND p.genPartyRoleId.partyRoleTypeId = :partyRoleTypeId")
    AdmPractitioner findPractitionerByPartyIdAndRoleTypeId(@Param("partyId") Long partyId, @Param("partyRoleTypeId") Long partyRoleTypeId);

    Optional<AdmPractitioner> findByGenPartyRoleIdPartyId(Long partyId);
}
