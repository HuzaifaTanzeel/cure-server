package com.example.demo.Repository.Medication;

import com.example.demo.Model.Medication.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug,Long> {

    @Query("SELECT d FROM Drug d WHERE d.str LIKE %:keyword%")
    List<Drug> findDrugByKeyword(@Param("keyword") String keyword);

}
