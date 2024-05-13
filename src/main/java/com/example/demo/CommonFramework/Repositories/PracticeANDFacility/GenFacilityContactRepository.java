package com.example.demo.CommonFramework.Repositories.PracticeANDFacility;

import com.example.demo.CommonFramework.Model.Facility.GenFacilityContactPoint;
import com.example.demo.CommonFramework.Model.Facility.GenFacilityContactPointId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenFacilityContactRepository extends JpaRepository<GenFacilityContactPoint, GenFacilityContactPointId> {
}
