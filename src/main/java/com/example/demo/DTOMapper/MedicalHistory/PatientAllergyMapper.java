package com.example.demo.DTOMapper.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.PatientAllergyDTO;
import com.example.demo.DTO.MedicalHistory.PatientAllergyReactionDTO;
import com.example.demo.Model.Allergy.PatientAllergy;
import com.example.demo.Model.Allergy.PatientAllergyReaction;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientAllergyMapper {

    private final ModelMapper modelMapper;

    public PatientAllergyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        TypeMap<PatientAllergy, PatientAllergyDTO> propertyMapper= modelMapper.createTypeMap(PatientAllergy.class, PatientAllergyDTO.class);

        propertyMapper.addMappings(
                mapper -> mapper.map(PatientAllergy::getPatientAllergyReactionList, PatientAllergyDTO::setPatientAllergyReactions)
        );

    }

    @Transactional
    public PatientAllergyDTO entityToDto(PatientAllergy entity) {
        return modelMapper.map(entity, PatientAllergyDTO.class);

    }

    @Transactional
    public PatientAllergy dtoToEntity(PatientAllergyDTO dto) {
        return modelMapper.map(dto, PatientAllergy.class);
    }

    @Transactional
    public List<PatientAllergyReaction> mapToReactions(List<PatientAllergyReactionDTO> reactionDTOs, PatientAllergy allergyEntity) {
        return reactionDTOs.stream()
                .map(reactionDTO -> {
                    PatientAllergyReaction reactionEntity = modelMapper.map(reactionDTO, PatientAllergyReaction.class);
                    //reactionEntity.setPatientAllergy(allergyEntity);
                    reactionEntity.setPatientAllergyId(allergyEntity.getPatientAllergyId());
                    allergyEntity.addPatientAllergyReactions(reactionEntity); // Bidirectional association
                    return reactionEntity;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PatientAllergyReactionDTO> mapToReactionDTOs(List<PatientAllergyReaction> reactions) {
        return reactions.stream()
                .map(reaction -> {
                    PatientAllergyReactionDTO reactionDTO = modelMapper.map(reaction, PatientAllergyReactionDTO.class);
                    // Explicitly set the patientAllergyId property from the source entity
                    reactionDTO.setPatientAllergyId(reaction.getPatientAllergy().getPatientAllergyId());
                    return reactionDTO;
                })
                .collect(Collectors.toList());
    }


}

