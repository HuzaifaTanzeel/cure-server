package com.example.demo.DTOMapper.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.FamilyMemberHistoryDTO;
import com.example.demo.Model.MedicalHistory.FamilyMemberHistory;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FamilyHistoryMapper {

    private final ModelMapper modelMapper;

    public FamilyHistoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    public FamilyMemberHistoryDTO entityToDto(FamilyMemberHistory entity) {
        return modelMapper.map(entity, FamilyMemberHistoryDTO.class);

    }

    @Transactional
    public FamilyMemberHistory dtoToEntity(FamilyMemberHistoryDTO dto) {
        return modelMapper.map(dto, FamilyMemberHistory.class);
    }


}
