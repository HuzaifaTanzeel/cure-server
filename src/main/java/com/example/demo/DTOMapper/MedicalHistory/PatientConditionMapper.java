package com.example.demo.DTOMapper.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.ClnPatientConditionDTO;
import com.example.demo.Model.MedicalCondition.ClnPatientProblem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientConditionMapper {

    private final ModelMapper modelMapper;

    public PatientConditionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Configure mappings: Entity to DTO
        modelMapper.createTypeMap(ClnPatientProblem.class, ClnPatientConditionDTO.class);
//                .addMappings(mapping -> {
//                    // Exclude auto-generated primary key
//                    mapping.skip(PatientAllergyDTO::setPatientAllergyId);
//                });

        // Configure mappings: DTO to Entity
        modelMapper.createTypeMap(ClnPatientConditionDTO.class, ClnPatientProblem.class)
                .addMappings(mapping -> {
                    // Exclude auto-generated primary key
                    mapping.skip(ClnPatientProblem::setProblemId);
                });
    }

    public ClnPatientConditionDTO entityToDto(ClnPatientProblem entity) {
        return modelMapper.map(entity, ClnPatientConditionDTO.class);
    }

    public ClnPatientProblem dtoToEntity(ClnPatientConditionDTO dto) {

        return modelMapper.map(dto, ClnPatientProblem.class);
    }
}
