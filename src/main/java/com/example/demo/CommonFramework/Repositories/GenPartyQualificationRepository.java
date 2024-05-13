package com.example.demo.CommonFramework.Repositories;

import com.example.demo.CommonFramework.Model.GenPartyQualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenPartyQualificationRepository extends JpaRepository<GenPartyQualification,Long> {
    List<GenPartyQualification> findByGenPartyPartyId(Long partyId);
}
