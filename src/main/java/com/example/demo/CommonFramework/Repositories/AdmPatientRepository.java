package com.example.demo.CommonFramework.Repositories;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Model.AdmPractitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmPatientRepository extends JpaRepository<AdmPatient,Long> {

    @Query("SELECT p FROM AdmPatient p WHERE p.genPartyRoleId.partyId = :partyId AND p.genPartyRoleId.partyRoleTypeId = :partyRoleTypeId")
    AdmPatient findPatientByPartyIdAndRoleTypeId(@Param("partyId") Long partyId, @Param("partyRoleTypeId") Long partyRoleTypeId);

//    @Query("SELECT DISTINCT p FROM AdmPatient p " +
//            "JOIN p.genPartyRole.genPartyContactPoint c " +
//            "WHERE c.contactPoint.contactNumber = :contactNumber")
//    List<AdmPatient> findByContactNumber(@Param("contactNumber") String contactNumber);
}
