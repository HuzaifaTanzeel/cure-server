package com.example.demo.CommonFramework.Repositories.Address;

import com.example.demo.CommonFramework.Model.Address.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<Cities,Long> {
    List<Cities> findByProvinceId(Long provinceId);
}
