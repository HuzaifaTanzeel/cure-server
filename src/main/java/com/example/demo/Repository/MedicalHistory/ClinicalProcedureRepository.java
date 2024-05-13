package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.MedicalHistory.ClinicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicalProcedureRepository extends JpaRepository<ClinicalProcedure,Long> {

    @Query("SELECT p FROM ClinicalProcedure p WHERE p.name LIKE %:keyword%")
    List<ClinicalProcedure> findProcedureByKeyword(@Param("keyword") String keyword);

}
