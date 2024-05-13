package com.example.demo.CommonFramework.Services.Implementation.ProfileDetails;

import com.example.demo.CommonFramework.DTO.CitiesDTO;
import com.example.demo.CommonFramework.DTO.CountryDTO;
import com.example.demo.CommonFramework.DTO.ProvincesDTO;
import com.example.demo.CommonFramework.Repositories.Address.CityRepository;
import com.example.demo.CommonFramework.Repositories.Address.CountryRepository;
import com.example.demo.CommonFramework.Repositories.Address.ProvinceRepository;
import com.example.demo.CommonFramework.Services.Interface.ProfileDetails.AddressInterface;
import com.example.demo.DTOMapper.PersonalProfiling.CityDTOMapper;
import com.example.demo.DTOMapper.PersonalProfiling.CountryDTOMapper;
import com.example.demo.DTOMapper.PersonalProfiling.ProvinceDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService implements AddressInterface {

    @Autowired
    private final CountryRepository countryRepository;

    @Autowired
    private final ProvinceRepository provinceRepository;

    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final CountryDTOMapper countryDTOMapper;
    @Autowired
    private final ProvinceDTOMapper provinceDTOMapper;
    @Autowired
    private final CityDTOMapper cityDTOMapper;

    public AddressService(CountryRepository countryRepository, ProvinceRepository provinceRepository, CityRepository cityRepository, CountryDTOMapper countryDTOMapper, ProvinceDTOMapper provinceDTOMapper, CityDTOMapper cityDTOMapper) {
        this.countryRepository = countryRepository;
        this.provinceRepository = provinceRepository;
        this.cityRepository = cityRepository;
        this.countryDTOMapper = countryDTOMapper;
        this.provinceDTOMapper = provinceDTOMapper;
        this.cityDTOMapper = cityDTOMapper;
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        return  countryRepository.findAll()
                    .stream()
                    .map(countryDTOMapper).collect(Collectors.toList());
    }


    @Override
    public List<ProvincesDTO> getProvinces(Long countryId){
        return provinceRepository.findByCountryId(countryId)
                .stream()
                .map(provinceDTOMapper).collect(Collectors.toList());

    }

    @Override
    public List<CitiesDTO> getCities(Long provinceId){
        return cityRepository.findByProvinceId(provinceId)
                .stream()
                .map(cityDTOMapper).collect(Collectors.toList());
    }




}
