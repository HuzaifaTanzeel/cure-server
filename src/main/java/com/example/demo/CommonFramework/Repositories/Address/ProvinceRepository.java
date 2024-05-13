package com.example.demo.CommonFramework.Repositories.Address;

import com.example.demo.CommonFramework.Model.Address.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Provinces,Long> {
    List<Provinces> findByCountryId(Long countryId);
}
