package com.example.demo.Repository.Diagnosis;

import com.example.demo.Model.Diagnosis.ClnServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ClnServiceRequest,Long> {

    List<ClnServiceRequest> findByEncounterId(Long EncounterId);
}
