package com.example.demo.CommonFramework.Repositories.Address;

import com.example.demo.CommonFramework.Model.Address.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Countries,Long> {

    //List<Countries> findAll();
}
