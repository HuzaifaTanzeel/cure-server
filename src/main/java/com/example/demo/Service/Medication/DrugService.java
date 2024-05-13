package com.example.demo.Service.Medication;

import com.example.demo.DTO.Medication.DrugDTO;

import java.util.List;

public interface DrugService {

    public List<DrugDTO> getDrugsByKeyword(String keyword);
}
