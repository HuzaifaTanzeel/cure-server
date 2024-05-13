package com.example.demo.CommonFramework.Repositories;

import com.example.demo.CommonFramework.Model.Practice.GenOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<GenOrganization,Long> {
}
