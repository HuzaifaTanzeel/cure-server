package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.CitiesDTO;
import com.example.demo.CommonFramework.Model.Address.Cities;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CityDTOMapper implements Function<Cities, CitiesDTO> {
    @Override
    public CitiesDTO apply(Cities cities) {
        return new CitiesDTO(
                cities.getCityId(),
                cities.getCityName()
        );
    }
}
