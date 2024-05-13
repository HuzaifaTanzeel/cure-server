package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.CountryDTO;
import com.example.demo.CommonFramework.Model.Address.Countries;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CountryDTOMapper implements Function<Countries, CountryDTO> {
    @Override
    public CountryDTO apply(Countries country) {
        return new CountryDTO(
                country.getCountryId(),
                country.getLongName()
        );
    }
}
