package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.Allergy.PatientAllergyReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientAllergyReactionRepository extends JpaRepository<PatientAllergyReaction,Long> {

    List<PatientAllergyReaction> findByPatientAllergyPatientAllergyId(Long patientAllergyId);
}
