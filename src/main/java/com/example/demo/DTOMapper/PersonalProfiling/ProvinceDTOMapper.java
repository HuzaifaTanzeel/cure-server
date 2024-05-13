package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.ProvincesDTO;
import com.example.demo.CommonFramework.Model.Address.Provinces;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProvinceDTOMapper implements Function<Provinces, ProvincesDTO> {
    @Override
    public ProvincesDTO apply(Provinces provinces) {
        return new ProvincesDTO(
                provinces.getId(),
                provinces.getProvinceName()
        );
    }
}
