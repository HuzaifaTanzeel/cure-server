package com.example.demo.DTOMapper.MedicalHistory;



import com.example.demo.DTO.MedicalHistory.PatientSocialHistoryDTO;
import com.example.demo.Model.MedicalHistory.PatientSocialHistory;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientSocialHistoryMapper {

    private final ModelMapper modelMapper;

    public PatientSocialHistoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

//        TypeMap<PatientAllergy, PatientAllergyDTO> propertyMapper= modelMapper.createTypeMap(PatientAllergy.class, PatientAllergyDTO.class);
//
//        propertyMapper.addMappings(
//                mapper -> mapper.map(PatientAllergy::getPatientAllergyReactionList, PatientAllergyDTO::setPatientAllergyReactions)
//        );

    }

    @Transactional
    public PatientSocialHistoryDTO entityToDto(PatientSocialHistory entity) {
        return modelMapper.map(entity, PatientSocialHistoryDTO.class);

    }

    @Transactional
    public PatientSocialHistory dtoToEntity(PatientSocialHistoryDTO dto) {
        return modelMapper.map(dto, PatientSocialHistory.class);
    }

    @Transactional
    public PatientSocialHistory dtoToEntity(PatientSocialHistoryDTO dto, PatientSocialHistory existingRecord) {
        existingRecord.setChewInd(dto.getChewInd());
        existingRecord.setCigarettesInd(dto.getCigarettesInd());
        existingRecord.setCigarsInd(dto.getCigarsInd());
        existingRecord.setPipesInd(dto.getPipesInd());
        existingRecord.setSnuffInd(dto.getSnuffInd());
        existingRecord.setComment(dto.getComment());
        existingRecord.setEncounterId(dto.getEncounterId());
        existingRecord.setPartyRoleTypeId(dto.getPartyRoleTypeId());
        existingRecord.setPatientId(dto.getPatientId());
        existingRecord.setRecordedDate(dto.getRecordedDate());
        existingRecord.setSmokingQuitDate(dto.getSmokingQuitDate());
        existingRecord.setSmokingStatusCd(dto.getSmokingStatusCd());
        existingRecord.setTobaccoPakPerDay(dto.getTobaccoPakPerDay());
        existingRecord.setTobaccoUserYears(dto.getTobaccoUserYears());
        return existingRecord;
    }




}

