package com.example.demo.Repository.Observation;

import com.example.demo.Model.Observation.DgnLoinc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DgnLoincRepository extends JpaRepository<DgnLoinc, String> {
    DgnLoinc findByLoincNum(String code);

}