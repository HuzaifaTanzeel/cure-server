package com.example.demo.DTOMapper.Diagnosis;

import com.example.demo.DTO.Diagnosis.ClnServiceRequestDTO;
import com.example.demo.Model.Diagnosis.ClnServiceRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestMapper {

    private final ModelMapper modelMapper;

    public ServiceRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;


        modelMapper.createTypeMap(ClnServiceRequest.class, ClnServiceRequestDTO.class)
                .addMappings(mapping -> {
                    // Exclude auto-generated primary key
                    mapping.skip(ClnServiceRequestDTO::setClnPatientDiagnosisList);
                });
    }

    @Transactional
    public ClnServiceRequestDTO entityToDto(ClnServiceRequest entity) {
        return modelMapper.map(entity, ClnServiceRequestDTO.class);

    }

    @Transactional
    public ClnServiceRequest dtoToEntity(ClnServiceRequestDTO dto) {
        return modelMapper.map(dto, ClnServiceRequest.class);
    }
}
