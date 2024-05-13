package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.PersonDTO;
import com.example.demo.CommonFramework.Model.GenPerson;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PersonDTOMapper implements Function<GenPerson, PersonDTO> {
    @Override
    public PersonDTO apply(GenPerson genPerson) {
        return new PersonDTO(
                genPerson.getPartyId(),
                genPerson.getFirstName(),
                genPerson.getMiddleName(),
                genPerson.getLastName(),
                genPerson.getBirthDate(),
                genPerson.getGenderCd(),
                genPerson.getMaritalStatusCd()
        );
    }
}
