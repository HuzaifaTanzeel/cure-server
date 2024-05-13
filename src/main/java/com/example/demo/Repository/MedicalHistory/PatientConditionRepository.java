package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.MedicalCondition.ClnPatientProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientConditionRepository extends JpaRepository<ClnPatientProblem,Long> {
    List<ClnPatientProblem> findByPatientId(Long patientId);
}
