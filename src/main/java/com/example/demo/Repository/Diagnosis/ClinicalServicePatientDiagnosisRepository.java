package com.example.demo.Repository.Diagnosis;

import com.example.demo.Model.Diagnosis.ClinicalServicePatientDiagnosis;
import com.example.demo.Model.Diagnosis.ClinicalServicePatientDiagnosisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalServicePatientDiagnosisRepository extends JpaRepository<ClinicalServicePatientDiagnosis, ClinicalServicePatientDiagnosisId> {

}
