package com.example.demo.CommonFramework.Repositories.PracticeANDFacility;

import com.example.demo.CommonFramework.Model.Facility.GenFacilityAddress;
import com.example.demo.CommonFramework.Model.Facility.GenFacilityAddressId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenFacilityAddressRepository extends JpaRepository<GenFacilityAddress, GenFacilityAddressId> {
}
