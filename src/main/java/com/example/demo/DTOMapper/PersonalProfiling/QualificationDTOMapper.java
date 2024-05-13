package com.example.demo.DTOMapper.PersonalProfiling;


import com.example.demo.CommonFramework.DTO.QualificationDTO;

import com.example.demo.CommonFramework.Model.GenPartyQualification;

import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Repositories.GenPartyQualificationRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRepository;
import com.example.demo.CommonFramework.Repositories.GenPersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class QualificationDTOMapper implements Function<GenPartyQualification, QualificationDTO> {

    @Autowired
    private GenPersonRepository genPersonRepository;

    @Autowired
    private GenPartyRepository genPartyRepository;

    @Autowired
    private GenPartyQualificationRepository genPartyQualificationRepository;

    private static final Logger logger = LoggerFactory.getLogger(AddressDTOMapper.class);

    @Override
    public QualificationDTO apply(GenPartyQualification genPartyQualification) {
        return new QualificationDTO(
                genPartyQualification.getQualificationId(),
                genPartyQualification.getGenParty().getPartyId(),
                genPartyQualification.getTitle(),
                genPartyQualification.getQualificationTypeCd(),
                genPartyQualification.getIssuerName(),
                genPartyQualification.getStartDate(),
                genPartyQualification.getEndDate(),
                genPartyQualification.isInProgressIndicator()
        );
    }





    @Transactional
    public GenPartyQualification mapToPartyQualification(QualificationDTO qualificationDTO) {
        try {
            Optional<GenPerson> genPersonOptional = genPersonRepository.findById(qualificationDTO.getPartyId());
            if(genPersonOptional.isPresent()){
                GenPerson genPerson=genPersonOptional.get();

                GenPartyQualification genPartyQualification=new GenPartyQualification(
                        genPerson.getPartyId(),
                        qualificationDTO.getTitle(),
                        qualificationDTO.getQualificationTypeCd(),
                        qualificationDTO.getIssuerName(),
                        qualificationDTO.getStartDate(),
                        qualificationDTO.getEndDate(),
                        qualificationDTO.isInProgressIndicator()
                );

                genPerson.addQualification(genPartyQualification);
                genPartyQualification.setGenParty(genPerson);
                genPartyRepository.save(genPerson);
                return genPartyQualificationRepository.save(genPartyQualification);

            }
            else {
                logger.error("User record not found: ");
                return null; // or throw an exception
            }
            // Set other properties as needed


        } catch (Exception e) {
            logger.error("Error creating party qualification", e);
            return null;
        }
    }

}

