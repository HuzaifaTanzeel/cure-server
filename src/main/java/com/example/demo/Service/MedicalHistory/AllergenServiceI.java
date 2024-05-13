package com.example.demo.Service.MedicalHistory;

import com.example.demo.DTO.MedicalHistory.AllergenDTO;

import java.util.List;

public interface AllergenServiceI {

    public List<AllergenDTO> getAllAllergens();

    public List<AllergenDTO> getAllergensByKeyword(String keyWord);
}
