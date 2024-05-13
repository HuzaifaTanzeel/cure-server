package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.MedicalHistory.PatientSocialHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientSocialHistoryRepo extends JpaRepository<PatientSocialHistory,Long> {

    List<PatientSocialHistory> findByPatientId(Long patientId);
}
