package com.example.demo.CommonFramework.Services.Implementation;

import com.example.demo.CommonFramework.DTO.IdentityTypeDTO;
import com.example.demo.CommonFramework.Repositories.IdentityTypeRepository;
import com.example.demo.CommonFramework.Services.Interface.ProfileDetails.IdentityInterface;
import com.example.demo.DTOMapper.PersonalProfiling.IdentityTypeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdentityService implements IdentityInterface {

    @Autowired
    private final IdentityTypeRepository identityTypeRepository;

    @Autowired
    private final IdentityTypeDTOMapper identityTypeDTOMapper;

    public IdentityService(IdentityTypeRepository identityTypeRepository, IdentityTypeDTOMapper identityTypeDTOMapper) {
        this.identityTypeRepository = identityTypeRepository;
        this.identityTypeDTOMapper = identityTypeDTOMapper;
    }

    @Override
    public List<IdentityTypeDTO> getIdentityTypes() {
        return  identityTypeRepository.findAll()
                .stream()
                .map(identityTypeDTOMapper).collect(Collectors.toList());
    }
}
