package com.example.demo.CommonFramework.Repositories.PracticeANDFacility;

import com.example.demo.CommonFramework.Model.Practice.HealthcareProviderOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HCProviderRepository extends JpaRepository<HealthcareProviderOrganization,Long> {
}
