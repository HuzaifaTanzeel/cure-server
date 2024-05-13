package com.example.demo.CommonFramework.Controllers;


import com.example.demo.DTO.MedicalHistory.*;
import com.example.demo.Enumerators.MedicalHistory.FamilyMemberRelationship;
import com.example.demo.Service.MedicalHistory.*;
import com.example.demo.Service.MedicalHistory.ServiceImpl.AllergenService;
import com.example.demo.Service.MedicalHistory.ServiceImpl.MedHistoryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PersistenceContext
@RestController
@RequestMapping("/api/Patient")
public class PatientController {

    private final AllergenService allergenService;

    private final AllergyHistoryService allergyHistoryService;

    private final MedHistoryServiceImpl allergyService;

    private final MedHistoryService medHistoryService;

    private final FamilyHistoryService familyHistoryService;

    private final SocialHistoryService socialHistoryService;

    private final SurgicalHistoryService surgicalHistoryService;

    @Autowired
    public PatientController(AllergenService allergenService, AllergyHistoryService allergyHistoryService, MedHistoryServiceImpl allergyService, MedHistoryService medHistoryService, FamilyHistoryService familyHistoryService, SocialHistoryService socialHistoryService, SurgicalHistoryService surgicalHistoryService) {
        this.allergenService = allergenService;
        this.allergyHistoryService = allergyHistoryService;
        this.allergyService = allergyService;
        this.medHistoryService = medHistoryService;
        this.familyHistoryService = familyHistoryService;
        this.socialHistoryService = socialHistoryService;
        this.surgicalHistoryService = surgicalHistoryService;
    }


    @GetMapping("/allergens")
    public ResponseEntity<List<AllergenDTO>> getAllAllergens() {
        try {
            List<AllergenDTO> allergyDTO = allergenService.getAllAllergens();
            return new ResponseEntity<>(allergyDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addAllergy")
    public ResponseEntity<PatientAllergyDTO> addAllergy(@RequestBody PatientAllergyDTO patientAllergyDTO) {
        try {

            PatientAllergyDTO patientAllergyResponse = allergyHistoryService.addPatientAllergy(patientAllergyDTO);
            return new ResponseEntity<>(patientAllergyResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addSurgicalHistory")
    public ResponseEntity<PatientSurgicalHistoryDTO> addPatientSurgicalHistory(@RequestBody PatientSurgicalHistoryDTO surgicalHistoryDTO) {
        try {
            PatientSurgicalHistoryDTO addedHistory = surgicalHistoryService.addPatientSurgicalHistory(surgicalHistoryDTO);
            return new ResponseEntity<>(addedHistory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllergies/{partyId}")
    public ResponseEntity<List<PatientAllergyDTO>> getAllergies(@PathVariable Long partyId) {
        try {
            List<PatientAllergyDTO> patientAllergies = allergyHistoryService.getPatientAllergies(partyId);
            return new ResponseEntity<>(patientAllergies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/conditions")
    public ResponseEntity<List<ClnConditionDTO>> getAllConditions() {
        try {
            List<ClnConditionDTO> conditionDTOs = medHistoryService.getAllConditions();
            return new ResponseEntity<>(conditionDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addMedCondition")
    public ResponseEntity<ClnPatientConditionDTO> addMedCondition(@RequestBody ClnPatientConditionDTO clnPatientConditionDTO) {
        try {
            ClnPatientConditionDTO conditionDTOResponse = medHistoryService.addPatientCondition(clnPatientConditionDTO);
            return new ResponseEntity<>(conditionDTOResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPatientMedicalConditions/{partyId}")
    public ResponseEntity<List<ClnPatientConditionDTO>> getPatientMedicalConditions(@PathVariable Long partyId) {
        try {
            List<ClnPatientConditionDTO> clnPatientConditionDTOS = medHistoryService.getPatientMedicalConditions(partyId);
            return new ResponseEntity<>(clnPatientConditionDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletePatientMedicalCondition/{id}")
    public ResponseEntity<?> deletePatientMedicalCondition(@PathVariable Long id) {
        try {
            medHistoryService.deletePatientMedicalCondition(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Medical Condition: " + e.getMessage());
        }
    }

    @GetMapping("/getPatientAllergyReactions/{patientAllergyId}")
    public ResponseEntity<List<PatientAllergyReactionDTO>> getPatientAllergyReactions(@PathVariable Long patientAllergyId) {
        List<PatientAllergyReactionDTO> allergyReactions = allergyHistoryService.getPatientAllergyReactions(patientAllergyId);
        return new ResponseEntity<>(allergyReactions, HttpStatus.OK);
    }


    @DeleteMapping("/del/{patientAllergyId}")
    public ResponseEntity<String> deleteAllergyHistory(@PathVariable Long patientAllergyId) {
        try {
            allergyHistoryService.deleteAllergyHistory(patientAllergyId);
            return ResponseEntity.ok("Allergy history deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient allergy with ID " + patientAllergyId + " not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting allergy history");
        }
    }

    @PostMapping("/addFamilyHistory")
    public ResponseEntity<Object> addFamilyHistory(@RequestBody FamilyMemberHistoryDTO familyMemberHistoryDTO) {
        try {
            FamilyMemberHistoryDTO savedFamilyHistory = familyHistoryService.addFamilyHistory(familyMemberHistoryDTO);
            return new ResponseEntity<>(savedFamilyHistory, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/getFamilyHistories/{patientId}")
    public ResponseEntity<List<FamilyMemberHistoryDTO>> getFamilyHistoryByPatientId(@PathVariable Long patientId) {
        try {
            List<FamilyMemberHistoryDTO> familyHistoryDTOList = familyHistoryService.getFamilyHistoryByPatientId(patientId);
            return new ResponseEntity<>(familyHistoryDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getFamilyHistory/{patientId}/relationship/{relationship}")
    public ResponseEntity<List<FamilyMemberHistoryDTO>> getFamilyHistoryByPatientRelationship(
            @PathVariable Long patientId,
            @PathVariable FamilyMemberRelationship relationship) {
        try {
            List<FamilyMemberHistoryDTO> familyHistoryDTOList = familyHistoryService.getFamilyHistoryByPatientRelationship(patientId, relationship);
            return new ResponseEntity<>(familyHistoryDTOList, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateFamilyHistory/{id}")
    public ResponseEntity<?> updateFamilyHistory(@PathVariable Long id, @RequestBody FamilyMemberHistoryDTO familyMemberHistoryDTO) {
        try {
            FamilyMemberHistoryDTO updateFamilyHistory = familyHistoryService.updateFamilyHistory(id, familyMemberHistoryDTO);
            return ResponseEntity.ok(updateFamilyHistory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Family history: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteFamilyHistory/{id}")
    public ResponseEntity<?> deleteFamilyHistory(@PathVariable Long id) {
        try {
            familyHistoryService.deletePatientFamilyHistory(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Family Member history: " + e.getMessage());
        }
    }


    @GetMapping("/getSurgicalHistories/{patientId}")
    public ResponseEntity<List<PatientSurgicalHistoryDTO>> getPatientSurgicalHistories(@PathVariable Long patientId) {
        try {
            List<PatientSurgicalHistoryDTO> patientSurgicalHistoryDTOS = surgicalHistoryService.getPatientSurgicalHistories(patientId);
            return new ResponseEntity<>(patientSurgicalHistoryDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/procedures")
    public ResponseEntity<List<ClinicalProcedureDTO>> getAllProcedures() {
        try {
            List<ClinicalProcedureDTO> clinicalProcedureDTOS = surgicalHistoryService.getAllProcedures();
            return new ResponseEntity<>(clinicalProcedureDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addSocialHistory")
    public ResponseEntity<PatientSocialHistoryDTO> addMedCondition(@RequestBody PatientSocialHistoryDTO patientSocialHistoryDTO) {
        try {
            PatientSocialHistoryDTO patientSocialHistoryDTOResponse = socialHistoryService.addPatientSocialHistory(patientSocialHistoryDTO);
            return new ResponseEntity<>(patientSocialHistoryDTOResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateSocialHistory/{id}")
    public ResponseEntity<?> updateSocialHistory(@PathVariable Long id, @RequestBody PatientSocialHistoryDTO patientSocialHistoryDTO) {
        try {
            PatientSocialHistoryDTO updatedSocialHistory = socialHistoryService.updatePatientSocialHistory(id, patientSocialHistoryDTO);
            return ResponseEntity.ok(updatedSocialHistory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update social history: " + e.getMessage());
        }
    }


    @DeleteMapping("/deleteSocialHistory/{id}")
    public ResponseEntity<?> deleteSocialHistory(@PathVariable Long id) {
        try {
            socialHistoryService.deletePatientSocialHistory(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete social history: " + e.getMessage());
        }
    }


    @GetMapping("/getSocialHistories/{partyId}")
    public ResponseEntity<List<PatientSocialHistoryDTO>> getSocialHistories(@PathVariable Long partyId) {
        try {
            List<PatientSocialHistoryDTO> patientSocialHistoryDTOS = socialHistoryService.getPatientSocialHistories(partyId);
            return new ResponseEntity<>(patientSocialHistoryDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}