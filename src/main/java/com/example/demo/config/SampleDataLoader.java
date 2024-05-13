package com.example.demo.config;

import com.example.demo.Model.Allergy.Allergen;
import com.example.demo.Model.MedicalCondition.ClnCondition;
import com.example.demo.Model.MedicalHistory.ClinicalProcedure;
import com.example.demo.Repository.MedicalHistory.AllergenRepository;
import com.example.demo.Repository.MedicalHistory.ClinicalProcedureRepository;
import com.example.demo.Repository.MedicalHistory.ClnConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final AllergenRepository allergenRepository; // Assuming you have a repository for Allergen

    private final ClnConditionRepository clnConditionRepository;

    private final ClinicalProcedureRepository clinicalProcedureRepository;
    @Autowired
    public SampleDataLoader(AllergenRepository allergenRepository, ClnConditionRepository clnConditionRepository, ClinicalProcedureRepository clinicalProcedureRepository) {
        this.allergenRepository = allergenRepository;
        this.clnConditionRepository = clnConditionRepository;
        this.clinicalProcedureRepository = clinicalProcedureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if database is empty
        if (allergenRepository.count() == 0) {
            // If empty, generate and save sample data
            List<Allergen> sampleAllergens = SampleDataGenerator.generateSampleAllergens();
            allergenRepository.saveAll(sampleAllergens);
        }

        if (clnConditionRepository.count() == 0) {
            // If empty, generate and save sample medical conditions data
            List<ClnCondition> sampleConditions = SampleDataGenerator.generateSampleMedicalConditions();
            clnConditionRepository.saveAll(sampleConditions);
        }

        if (clinicalProcedureRepository.count() == 0) {
            // If empty, generate and save sample data
            List<ClinicalProcedure> sampleProcedures = SampleDataGenerator.generateSampleProcedures();
            clinicalProcedureRepository.saveAll(sampleProcedures);
        }
    }
}
