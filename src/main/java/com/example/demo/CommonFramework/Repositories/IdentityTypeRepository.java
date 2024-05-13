package com.example.demo.CommonFramework.Repositories;

import com.example.demo.CommonFramework.Model.Identity.GenIdentityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityTypeRepository extends JpaRepository<GenIdentityType,Long> {
}
