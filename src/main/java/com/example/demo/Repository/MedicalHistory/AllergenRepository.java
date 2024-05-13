package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.Allergy.Allergen;
import com.example.demo.Model.MedicalHistory.ClinicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergenRepository extends JpaRepository<Allergen,Long> {

    @Query("SELECT a FROM Allergen a WHERE a.allergyName LIKE %:keyword%")
    List<Allergen> findAllergenByKeyword(@Param("keyword") String keyword);
}
