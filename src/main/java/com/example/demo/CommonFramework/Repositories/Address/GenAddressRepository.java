package com.example.demo.CommonFramework.Repositories.Address;

import com.example.demo.CommonFramework.Model.Address.GenAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenAddressRepository extends JpaRepository<GenAddress,Long> {
}
