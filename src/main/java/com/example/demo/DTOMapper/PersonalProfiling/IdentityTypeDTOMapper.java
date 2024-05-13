package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.IdentityTypeDTO;
import com.example.demo.CommonFramework.Model.Identity.GenIdentityType;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class IdentityTypeDTOMapper implements Function<GenIdentityType, IdentityTypeDTO> {


    @Override
    public IdentityTypeDTO apply(GenIdentityType genIdentityType) {
        return new IdentityTypeDTO(
                genIdentityType.getIdentityTypeId(),
                genIdentityType.getIdentityName()
        );
    }
}
