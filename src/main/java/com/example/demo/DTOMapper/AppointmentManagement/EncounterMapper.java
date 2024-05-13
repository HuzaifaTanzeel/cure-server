package com.example.demo.DTOMapper.AppointmentManagement;

import com.example.demo.DTO.AppointmentManagement.EncounterDTO;
import com.example.demo.Model.AppointmentManagement.Encounter;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncounterMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public EncounterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;


        modelMapper.createTypeMap(EncounterDTO.class, Encounter.class)
                .addMappings(mapping -> {
                    // Exclude auto-generated primary key
                    mapping.skip(Encounter::setEncounterId);
                });
    }

    @Transactional
    public EncounterDTO entityToDto(Encounter entity) {
        return modelMapper.map(entity, EncounterDTO.class);
    }

    @Transactional
    public Encounter dtoToEntity(EncounterDTO dto) {
        return modelMapper.map(dto, Encounter.class);
    }
}