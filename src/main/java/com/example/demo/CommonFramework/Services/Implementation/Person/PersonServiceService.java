package com.example.demo.CommonFramework.Services.Implementation.Person;

import com.example.demo.CommonFramework.DTO.PersonDTO;
import com.example.demo.CommonFramework.DTO.QualificationDTO;
import com.example.demo.CommonFramework.Model.GenPartyQualification;
import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Model.RegistrationRequest;
import com.example.demo.CommonFramework.Repositories.GenPartyQualificationRepository;
import com.example.demo.CommonFramework.Repositories.GenPersonRepository;
import com.example.demo.CommonFramework.Services.Interface.Person.PersonServiceInterface;
import com.example.demo.DTOMapper.PersonalProfiling.PersonDTOMapper;
import com.example.demo.DTOMapper.PersonalProfiling.QualificationDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceService implements PersonServiceInterface {

    @Autowired
    private final GenPersonRepository genPersonRepository;

    @Autowired
    private final GenPartyQualificationRepository genPartyQualificationRepository;

    @Autowired
    private final PersonDTOMapper personDTOMapper;

    @Autowired
    private final QualificationDTOMapper qualificationDTOMapper;

    public PersonServiceService(GenPersonRepository genPersonRepository, GenPartyQualificationRepository genPartyQualificationRepository, PersonDTOMapper personDTOMapper, QualificationDTOMapper qualificationDTOMapper) {
        this.genPersonRepository = genPersonRepository;
        this.genPartyQualificationRepository = genPartyQualificationRepository;
        this.personDTOMapper = personDTOMapper;
        this.qualificationDTOMapper = qualificationDTOMapper;
    }


    @Override
    public GenPerson addPersonalDetails(int PartyTypeCD, RegistrationRequest registrationRequestPractitioner) {
        GenPerson savedPerson= new GenPerson(
                PartyTypeCD,
                registrationRequestPractitioner.getFirstName(),
                registrationRequestPractitioner.getLastName()
        );

        return genPersonRepository.save(savedPerson);


    }

    @Transactional
    public PersonDTO getPersonalDetails(Long partyId){
        Optional<GenPerson> genPersonOptional = genPersonRepository.findById(partyId);
        if (genPersonOptional.isPresent()) {
            GenPerson genPerson = genPersonOptional.get();
            PersonDTO personDTO = personDTOMapper.apply(genPerson);
            return  personDTO;
        } else {
            return new PersonDTO();
        }
    }

    @Transactional
    public PersonDTO updatePersonalDetails(Long partyId, PersonDTO updatedPersonDetails) {
        Optional<GenPerson> genPersonOptional = genPersonRepository.findById(partyId);
        if (genPersonOptional.isPresent()) {
            GenPerson genPerson = genPersonOptional.get();

            // Update fields
            genPerson.setFirstName(updatedPersonDetails.getFirstName());
            genPerson.setMiddleName(updatedPersonDetails.getMiddleName());
            genPerson.setLastName(updatedPersonDetails.getLastName());
            genPerson.setSuffix(updatedPersonDetails.getSuffix());
            genPerson.setBirthDate(updatedPersonDetails.getBirthDate());
            genPerson.setGenderCd(updatedPersonDetails.getGenderCd());
            genPerson.setMaritalStatusCd(updatedPersonDetails.getMaritalStatusCd());

            // Save the updated entity
            genPersonRepository.save(genPerson);

            // Return the updated details
            return personDTOMapper.apply(genPerson);
        } else {
            return new PersonDTO();
        }
    }


    @Transactional
    public QualificationDTO createQualification(QualificationDTO qualificationDTO){
            GenPartyQualification genPartyQualification=qualificationDTOMapper.mapToPartyQualification(qualificationDTO);
            return qualificationDTOMapper.apply(genPartyQualification);
    }

    @Transactional
    public List<QualificationDTO> getAllQualifications(Long partyId){
        return genPartyQualificationRepository.findByGenPartyPartyId(partyId)
                .stream()
                .map(qualificationDTOMapper).collect(Collectors.toList());
    }
}
