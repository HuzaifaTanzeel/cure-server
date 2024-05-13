package com.example.demo.Repository.MedicalHistory;

import com.example.demo.Model.MedicalHistory.PatientSurgicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientSurgHistRepository extends JpaRepository<PatientSurgicalHistory,Long> {

    List<PatientSurgicalHistory> findByPatientId(Long patientId);
}
