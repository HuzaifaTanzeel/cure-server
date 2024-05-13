package com.example.demo.Repository.Medication;

import com.example.demo.Model.Medication.MedicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRequestRepository extends JpaRepository<MedicationRequest,Long> {

    List<MedicationRequest> findByEncounterId(Long EncounterId);
}
