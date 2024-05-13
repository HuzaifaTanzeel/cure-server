package com.example.demo.Service.MedicalHistory.ServiceImpl;

import com.example.demo.DTO.MedicalHistory.AllergenDTO;
import com.example.demo.DTO.MedicalHistory.ClinicalProcedureDTO;
import com.example.demo.Model.Allergy.Allergen;
import com.example.demo.Model.MedicalHistory.ClinicalProcedure;
import com.example.demo.Repository.MedicalHistory.AllergenRepository;
import com.example.demo.Service.MedicalHistory.AllergenServiceI;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllergenService implements AllergenServiceI {


    private final AllergenRepository allergenRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AllergenService(AllergenRepository allergenRepository, ModelMapper modelMapper) {
        this.allergenRepository = allergenRepository;
        this.modelMapper = modelMapper;
    }

    public List<AllergenDTO> getAllAllergens() {
        List<Allergen> allergens = allergenRepository.findAll();
        return allergens.stream()
                .map(allergen -> modelMapper.map(allergen, AllergenDTO.class))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public List<AllergenDTO> getAllergensByKeyword(String keyWord){
        List<Allergen> allergens = allergenRepository.findAllergenByKeyword(keyWord);
        return allergens.stream()
                .map(allergen -> modelMapper.map(allergen, AllergenDTO.class))
                .collect(Collectors.toList());
    }
}
