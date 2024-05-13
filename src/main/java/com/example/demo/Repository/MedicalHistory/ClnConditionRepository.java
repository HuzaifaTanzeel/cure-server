package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.Allergy.Allergen;
import com.example.demo.Model.MedicalCondition.ClnCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClnConditionRepository extends JpaRepository<ClnCondition,Long> {

    @Query("SELECT c FROM ClnCondition c WHERE c.name LIKE %:keyword%")
    List<ClnCondition> findConditionsByKeyword(@Param("keyword") String keyword);
}
