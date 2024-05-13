package com.example.demo.DTOMapper.Medication;
import com.example.demo.DTO.Medication.DrugDTO;
import com.example.demo.Model.Medication.Drug;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class DrugMapper {

    private final ModelMapper modelMapper;

    public DrugMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;


//        modelMapper.createTypeMap(DrugDTO.class, Drug.class)
//                .addMappings(mapping -> {
//                    // Exclude auto-generated primary key
//                    mapping.skip(Dr);
//                });
    }

    @Transactional
    public DrugDTO entityToDto(Drug entity) {
        return modelMapper.map(entity, DrugDTO.class);

    }

    @Transactional
    public Drug dtoToEntity(DrugDTO dto) {
        return modelMapper.map(dto, Drug.class);
    }
}