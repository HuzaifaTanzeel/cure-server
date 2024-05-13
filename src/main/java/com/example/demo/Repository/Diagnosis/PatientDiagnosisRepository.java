package com.example.demo.Repository.Diagnosis;

import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<ClnPatientDiagnosis,Long> {

    List<ClnPatientDiagnosis> findByEncounterId(Long EncounterId);
}
