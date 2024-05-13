package com.example.demo.config;

import com.example.demo.CommonFramework.Enumerators.AllergyCategory;
import com.example.demo.CommonFramework.Enumerators.ProcedureCategory;
import com.example.demo.Model.Allergy.Allergen;
import com.example.demo.Model.MedicalCondition.ClnCondition;
import com.example.demo.Model.MedicalHistory.ClinicalProcedure;

import java.util.ArrayList;
import java.util.List;

public class SampleDataGenerator {

    public static List<Allergen> generateSampleAllergens() {
        List<Allergen> allergens = new ArrayList<>();

        // Sample Allergens

        Allergen allergen1 = new Allergen();
        allergen1.setAllergyName("Peanuts");
        allergen1.setAllergyCategoryCd(AllergyCategory.FOOD);
        allergen1.setDescription("Allergy to peanuts");
        allergens.add(allergen1);

        Allergen allergen2 = new Allergen();
        allergen2.setAllergyName("Penicillin");
        allergen2.setAllergyCategoryCd(AllergyCategory.MEDICATION);
        allergen2.setDescription("Allergy to penicillin");
        allergens.add(allergen2);



        // Add more sample allergens as needed

        return allergens;
    }

    public static List<ClnCondition> generateSampleMedicalConditions() {
        List<ClnCondition> medicalConditions = new ArrayList<>();

        // Sample Medical Conditions
        ClnCondition condition1 = new ClnCondition();
        condition1.setConditionCode("C001");
        condition1.setName("Asthma");
        condition1.setDefinition("A chronic respiratory condition characterized by difficulty breathing due to inflammation and narrowing of the airways.");
        condition1.setCodeSystem("ICD-10");
        medicalConditions.add(condition1);

        ClnCondition condition2 = new ClnCondition();
        condition2.setConditionCode("C002");
        condition2.setName("Hypertension");
        condition2.setDefinition("A condition in which the force of the blood against the artery walls is too high.");
        condition2.setCodeSystem("ICD-10");
        medicalConditions.add(condition2);

        // Add more sample medical conditions
        ClnCondition condition3 = new ClnCondition();
        condition3.setConditionCode("C003");
        condition3.setName("Diabetes Mellitus");
        condition3.setDefinition("A group of diseases that result in high blood sugar (glucose) levels over a prolonged period.");
        condition3.setCodeSystem("ICD-10");
        medicalConditions.add(condition3);

        return medicalConditions;
    }

    public static List<ClinicalProcedure> generateSampleProcedures() {
        List<ClinicalProcedure> procedures = new ArrayList<>();

        // Sample Clinical Procedures
        ClinicalProcedure procedure1 = new ClinicalProcedure();
        procedure1.setCode("A01.1");
        procedure1.setName("Appendectomy");
        procedure1.setCategory(ProcedureCategory.SURGICAL_PROCEDURE);
        procedure1.setDescription("Surgical removal of the appendix.");
        procedures.add(procedure1);

        ClinicalProcedure procedure2 = new ClinicalProcedure();
        procedure2.setCode("B20.1");
        procedure2.setName("Electrocardiogram (ECG)");
        procedure2.setCategory(ProcedureCategory.DIAGNOSTIC_PROCEDURE);
        procedure2.setDescription("Test that checks for problems with the electrical activity of your heart.");
        procedures.add(procedure2);

        // Add more sample procedures
        ClinicalProcedure procedure3 = new ClinicalProcedure();
        procedure3.setCode("C30.1");
        procedure3.setName("Colonoscopy");
        procedure3.setCategory(ProcedureCategory.DIAGNOSTIC_PROCEDURE);
        procedure3.setDescription("Procedure used to detect changes or abnormalities in the colon and rectum.");
        procedures.add(procedure3);

        return procedures;
    }
}
