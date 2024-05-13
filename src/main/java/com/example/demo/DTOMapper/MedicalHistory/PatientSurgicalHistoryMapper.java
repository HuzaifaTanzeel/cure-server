package com.example.demo.DTOMapper.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.PatientSurgicalHistoryDTO;
import com.example.demo.Model.MedicalHistory.PatientSurgicalHistory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientSurgicalHistoryMapper {

    private final ModelMapper modelMapper;

    public PatientSurgicalHistoryMapper(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(PatientSurgicalHistoryDTO.class, PatientSurgicalHistory.class)
                .addMappings(mapping -> {
                    mapping.map(src -> src.getClinicalProcedure().getProcedureId(), PatientSurgicalHistory::setProcedureId);
                    mapping.map(src -> src.getClinicalProcedure().getName(),PatientSurgicalHistory::setSurgeryName);
                });


    }

    public PatientSurgicalHistoryDTO entityToDto(PatientSurgicalHistory entity) {
        return modelMapper.map(entity, PatientSurgicalHistoryDTO.class);
    }

    public PatientSurgicalHistory dtoToEntity(PatientSurgicalHistoryDTO dto) {

        return modelMapper.map(dto, PatientSurgicalHistory.class);
    }
}
