package com.example.demo.DTOMapper.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.ClnConditionDTO;
import com.example.demo.Model.MedicalCondition.ClnCondition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClnConditionMapper {

    private final ModelMapper modelMapper;

    public ClnConditionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClnConditionDTO entityToDto(ClnCondition entity) {
        return modelMapper.map(entity, ClnConditionDTO.class);
    }

    public ClnCondition dtoToEntity(ClnConditionDTO dto) {
        return modelMapper.map(dto, ClnCondition.class);
    }
}