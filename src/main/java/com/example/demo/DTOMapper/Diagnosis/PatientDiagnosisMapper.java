package com.example.demo.DTOMapper.Diagnosis;

import com.example.demo.DTO.Diagnosis.ClnPatientDiagnosisDTO;
import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PatientDiagnosisMapper {

    private final ModelMapper modelMapper;

    public PatientDiagnosisMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;


        // Configure mappings: DTO to Entity
        modelMapper.createTypeMap(ClnPatientDiagnosisDTO.class, ClnPatientDiagnosis.class)
                .addMappings(mapping -> {
                    // Exclude auto-generated primary key
                    mapping.skip(ClnPatientDiagnosis::setDiagnosisId);
                });




    }


    @Transactional
    public ClnPatientDiagnosisDTO entityToDto(ClnPatientDiagnosis entity) {
        return modelMapper.map(entity, ClnPatientDiagnosisDTO.class);
    }

    @Transactional
    public ClnPatientDiagnosis dtoToEntity(ClnPatientDiagnosisDTO dto) {

        return modelMapper.map(dto, ClnPatientDiagnosis.class);
    }
}

