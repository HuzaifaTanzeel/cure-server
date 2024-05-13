package com.example.demo.Service.Medication.ServiceImpl;

import com.example.demo.DTO.Medication.DrugDTO;
import com.example.demo.DTOMapper.Medication.DrugMapper;
import com.example.demo.Model.Medication.Drug;
import com.example.demo.Repository.Medication.DrugRepository;
import com.example.demo.Service.Medication.DrugService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;

    private final DrugMapper drugMapper;

    @Autowired
    public DrugServiceImpl(DrugRepository drugRepository, DrugMapper drugMapper) {
        this.drugRepository = drugRepository;
        this.drugMapper = drugMapper;
    }

    @Transactional
    @Override
    public List<DrugDTO> getDrugsByKeyword(String keyword) {
        List<Drug> drugs = drugRepository.findDrugByKeyword(keyword);
        return drugs.stream()
                .map(drugMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
