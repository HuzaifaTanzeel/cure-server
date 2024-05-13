package com.example.demo.Service.MedicalHistory.ServiceImpl;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.DTO.MedicalHistory.PatientAllergyDTO;
import com.example.demo.DTO.MedicalHistory.PatientAllergyReactionDTO;
import com.example.demo.DTOMapper.MedicalHistory.PatientAllergyMapper;
import com.example.demo.Model.Allergy.Allergen;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.Allergy.PatientAllergy;
import com.example.demo.Model.Allergy.PatientAllergyReaction;
import com.example.demo.Repository.AppointmentManagement.EncounterRepository;
import com.example.demo.Repository.MedicalHistory.AllergenRepository;
import com.example.demo.Repository.MedicalHistory.PatientAllergyReactionRepository;
import com.example.demo.Repository.MedicalHistory.PatientAllergyRepository;
import com.example.demo.Service.MedicalHistory.AllergyHistoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AllergyHistoryServiceImpl implements AllergyHistoryService {


    @Autowired
    private final PatientAllergyRepository patientAllergyRepository;

    @Autowired
    private final AdmPatientRepository admPatientRepository;

    @Autowired
    private final AllergenRepository allergenRepository;


    @Autowired
    private final EncounterRepository encounterRepository;

    @Autowired
    private final PatientAllergyMapper patientAllergyMapper;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final PatientAllergyReactionRepository patientAllergyReactionRepository;

    public AllergyHistoryServiceImpl(PatientAllergyRepository patientAllergyRepository, AdmPatientRepository admPatientRepository, AllergenRepository allergenRepository, EncounterRepository encounterRepository, PatientAllergyMapper patientAllergyMapper, ModelMapper modelMapper, PatientAllergyReactionRepository patientAllergyReactionRepository) {
        this.patientAllergyRepository = patientAllergyRepository;
        this.admPatientRepository = admPatientRepository;
        this.allergenRepository = allergenRepository;
        this.encounterRepository = encounterRepository;
        this.patientAllergyMapper = patientAllergyMapper;
        this.modelMapper = modelMapper;
        this.patientAllergyReactionRepository = patientAllergyReactionRepository;
    }


    @Transactional
    @Override
    public PatientAllergyDTO addPatientAllergy(PatientAllergyDTO patientAllergyDTO) {
        PatientAllergy patientAllergy = patientAllergyMapper.dtoToEntity(patientAllergyDTO);
        patientAllergy=patientAllergyRepository.save(patientAllergy);

        List<PatientAllergyReaction> patientAllergyReactions = patientAllergyMapper.mapToReactions(patientAllergyDTO.getPatientAllergyReactions(), patientAllergy);
        patientAllergyReactionRepository.saveAll(patientAllergyReactions);

        AdmPatient admPatient = admPatientRepository.findPatientByPartyIdAndRoleTypeId(
                patientAllergyDTO.getPatientId(),
                patientAllergyDTO.getPartyRoleTypeId()
        );
        Optional<Allergen> allergen = allergenRepository.findById(patientAllergyDTO.getAllergen().getAllergenId());

        // Check if encounterId is not null
        if (patientAllergyDTO.getEncounterId() != null) {
            Optional<Encounter> encounter = encounterRepository.findById(patientAllergyDTO.getEncounterId());
            if (encounter.isPresent()) {
                Encounter encounterRecord = encounter.get();
//              patientAllergy.setEncounter(encounterRecord);
                encounterRecord.addPatientAllergies(patientAllergy);
                encounterRepository.save(encounterRecord);
            } else {
                throw new RuntimeException("Encounter with ID " + patientAllergyDTO.getEncounterId() + " not found");
            }
        }

        if (allergen.isPresent()) {
            Allergen allergenData = allergen.get();
//          patientAllergy.setAllergen(allergenData);
            allergenData.addPatientAllergies(patientAllergy);
            allergenRepository.save(allergenData);
        }

        admPatient.addPatientAllergies(patientAllergy);
        //patientAllergy.setAdmPatient(admPatient);
        admPatientRepository.save(admPatient);


        patientAllergyRepository.save(patientAllergy);
        return patientAllergyMapper.entityToDto(patientAllergy);
    }


    @Transactional
    @Override
    public List<PatientAllergyDTO> getPatientAllergies(Long partyId) {
        List<PatientAllergy> patientAllergies = patientAllergyRepository.findByPatientId(partyId);
        List<PatientAllergyDTO> patientAllergyDTOs = new ArrayList<>();


        for (PatientAllergy patientAllergy : patientAllergies) {
            patientAllergyDTOs.add(patientAllergyMapper.entityToDto(patientAllergy));
        }

        return patientAllergyDTOs;
    }

    @Transactional
    @Override
    public List<PatientAllergyReactionDTO> getPatientAllergyReactions(Long patientAllergyId) {
        List<PatientAllergyReaction> patientAllergyReactions = patientAllergyReactionRepository.findByPatientAllergyPatientAllergyId(patientAllergyId);
        List<PatientAllergyReactionDTO> patientAllergyReactionDTOS = new ArrayList<>();

        patientAllergyReactionDTOS=patientAllergyMapper.mapToReactionDTOs(patientAllergyReactions);

        return patientAllergyReactionDTOS;
    }


    @Transactional
    @Override
    public void deleteAllergyHistory(Long patientAllergyId) {
        // Find the PatientAllergy entity to delete
        Optional<PatientAllergy> optionalPatientAllergy = patientAllergyRepository.findById(patientAllergyId);
        if (optionalPatientAllergy.isPresent()) {
            PatientAllergy patientAllergy = optionalPatientAllergy.get();

            // Delete the PatientAllergy entity
            patientAllergyRepository.delete(patientAllergy);
        } else {
            // Handle case where PatientAllergy entity with the given ID is not found
            throw new EntityNotFoundException("PatientAllergy with ID " + patientAllergyId + " not found");
        }
    }
}
