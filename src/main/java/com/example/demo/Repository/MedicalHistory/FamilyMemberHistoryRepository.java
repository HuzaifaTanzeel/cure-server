package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Enumerators.MedicalHistory.FamilyMemberRelationship;
import com.example.demo.Model.MedicalHistory.FamilyMemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMemberHistoryRepository extends JpaRepository<FamilyMemberHistory,Long> {

    List<FamilyMemberHistory> findByPatientId(Long patientId);
    List<FamilyMemberHistory> findByPatientIdAndRelationshipCd(Long patientId, FamilyMemberRelationship relationshipCd);


    boolean existsByPatientIdAndRelationshipCd(Long patientId, FamilyMemberRelationship relationshipCd);



//    @Query("SELECT fmh FROM FamilyMemberHistory fmh WHERE fmh.patientId = :patientId")
//    List<FamilyMemberHistory> findAllByPatientId(@Param("patientId") Long patientId);

}
