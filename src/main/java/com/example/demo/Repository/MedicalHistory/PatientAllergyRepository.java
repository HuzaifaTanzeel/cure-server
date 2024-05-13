package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.Allergy.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientAllergyRepository extends JpaRepository<PatientAllergy,Long> {

    List<PatientAllergy> findByPatientId(Long patientId);
}
