package com.example.demo.CommonFramework.Controllers;

import com.example.demo.CommonFramework.DTO.*;
import com.example.demo.CommonFramework.Services.Implementation.ProfileDetails.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practitioner")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @GetMapping("/allCountries")
    public ResponseEntity<List<CountryDTO>> getCountries(){
        List<CountryDTO> countries = addressService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/byCountry/{countryId}")
    public ResponseEntity<List<ProvincesDTO>> getProvincesByCountryId(@PathVariable Long countryId) {
        List<ProvincesDTO> provinces = addressService.getProvinces(countryId);
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

    @GetMapping("/byProvince/{provinceId}")
    public ResponseEntity<List<CitiesDTO>> getCitiesByProvinceId(@PathVariable Long provinceId) {
        List<CitiesDTO> cities = addressService.getCities(provinceId);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


}
