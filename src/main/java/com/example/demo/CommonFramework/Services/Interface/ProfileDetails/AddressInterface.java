package com.example.demo.CommonFramework.Services.Interface.ProfileDetails;

import com.example.demo.CommonFramework.DTO.CitiesDTO;
import com.example.demo.CommonFramework.DTO.CountryDTO;
import com.example.demo.CommonFramework.DTO.ProvincesDTO;

import java.util.List;

public interface AddressInterface {

    public List<CountryDTO> getAllCountries();
    public List<ProvincesDTO> getProvinces(Long countryId);

    public List<CitiesDTO> getCities(Long provinceId);

}
