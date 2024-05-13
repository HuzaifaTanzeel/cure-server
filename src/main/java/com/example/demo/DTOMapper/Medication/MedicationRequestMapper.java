package com.example.demo.DTOMapper.Medication;

import com.example.demo.DTO.Medication.MedicationRequestDTO;
import com.example.demo.Model.Medication.MedicationRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MedicationRequestMapper {

    private final ModelMapper modelMapper;

    public MedicationRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;


        modelMapper.createTypeMap(MedicationRequest.class, MedicationRequestDTO.class)
                .addMappings(mapping -> {
                    // Exclude auto-generated primary key
                    mapping.skip(MedicationRequestDTO::setClnPatientDiagnosisList);
                });
    }

    @Transactional
    public MedicationRequestDTO entityToDto(MedicationRequest entity) {
        return modelMapper.map(entity, MedicationRequestDTO.class);

    }

    @Transactional
    public MedicationRequest dtoToEntity(MedicationRequestDTO dto) {
        return modelMapper.map(dto, MedicationRequest.class);
    }
}
