package com.example.demo.Repository.Medication;

import com.example.demo.Model.Medication.MedRequestPatientDiagnosis;
import com.example.demo.Model.Medication.MedRequestPatientDiagnosisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicationRequestPatientDiagnosisRepository extends JpaRepository<MedRequestPatientDiagnosis, MedRequestPatientDiagnosisId> {
}
