package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.PartyIdentityDTO;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import com.example.demo.CommonFramework.Model.Identity.GenIdentityType;
import com.example.demo.CommonFramework.Model.Identity.GenPartyIdentity;
import com.example.demo.CommonFramework.Model.Identity.GenPartyIdentityId;
import com.example.demo.CommonFramework.Repositories.GenPartyIdentityRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleRepository;
import com.example.demo.CommonFramework.Repositories.IdentityTypeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class IdentityDTOMapper implements Function<GenPartyIdentity, PartyIdentityDTO> {

   @Autowired
   private GenPartyRoleRepository genPartyRoleRepository;

   @Autowired
   private IdentityTypeRepository identityTypeRepository;

   @Autowired
   private GenPartyIdentityRepository genPartyIdentityRepository;

    private static final Logger logger = LoggerFactory.getLogger(AddressDTOMapper.class);


    @Override
    public PartyIdentityDTO apply(GenPartyIdentity genPartyIdentity) {
        return new PartyIdentityDTO(
                genPartyIdentity.getGenPartyRole().getGenPartyRoleId().getPartyId(),
                genPartyIdentity.getGenPartyRole().getGenPartyRoleId().getPartyRoleTypeId(),
                genPartyIdentity.getGenIdentityType().getIdentityTypeId(),
                genPartyIdentity.getGenIdentityType().getIdentityName(),
                genPartyIdentity.getValue(),
                genPartyIdentity.getValidFromDt(),
                genPartyIdentity.getValidToDt()
        );
    }


//    public GenPartyIdentity mapToPartyIdentity(PartyIdentityDTO partyIdentityDTO) {
//        try {
//            GenPartyRoleId genPartyRoleId = new GenPartyRoleId(partyIdentityDTO.getPartyId(),partyIdentityDTO.getPartyRoleTypeId());
//            Optional<GenPartyRole> genPartyRole=genPartyRoleRepository.findById(genPartyRoleId);
//            if(genPartyRole.isPresent()) {
//                GenPartyRole savedRole = genPartyRole.get();
//
//                //Linking address with Party & role
//
//
//                GenPartyIdentity genPartyIdentity = new GenPartyIdentity(
//                        new GenPartyIdentityId(
//                                genPartyRoleId,
//                                partyIdentityDTO.getIdentityTypeId()
//                        ),
//                        partyIdentityDTO.getValue(),
//                        partyIdentityDTO.getValidFromDt(),
//                        partyIdentityDTO.getValidToDt()
//                );
//
//
//
//                Optional<GenIdentityType> genIdentityType=identityTypeRepository.findById(partyIdentityDTO.getIdentityTypeId());
//
//
//                genIdentityType.ifPresent(genPartyIdentity::setGenIdentityType);
//
//                genPartyIdentity.setGenIdentityType(genIdentityType.get());
//
//
//                identityTypeRepository.save(genIdentityType.get());
//
//
//                savedRole.addPartyIdentity(genPartyIdentity);
//                genPartyIdentity.setGenPartyRole(savedRole);
//
//
//
//
//
//                genPartyRoleRepository.save(savedRole);
//
//                return genPartyIdentityRepository.save(genPartyIdentity);
//            }
//            else {
//                logger.error("GenPartyRole not found for ID: {}", genPartyRoleId);
//                return null; // or throw an exception
//            }
//            // Set other properties as needed
//
//
//        } catch (Exception e) {
//            logger.error("Error creating party Identity", e);
//            return null;
//        }
//    }

    @Transactional
    public GenPartyIdentity mapToPartyIdentity(PartyIdentityDTO partyIdentityDTO) {
        try {
            GenPartyRoleId genPartyRoleId = new GenPartyRoleId(partyIdentityDTO.getPartyId(), partyIdentityDTO.getPartyRoleTypeId());
            Optional<GenPartyRole> genPartyRoleOpt = genPartyRoleRepository.findById(genPartyRoleId);

            if (genPartyRoleOpt.isPresent()) {
                GenPartyRole savedRole = genPartyRoleOpt.get();

                GenPartyIdentityId genPartyIdentityId = new GenPartyIdentityId(genPartyRoleId, partyIdentityDTO.getIdentityTypeId());
                GenPartyIdentity genPartyIdentity = new GenPartyIdentity(genPartyIdentityId,
                        partyIdentityDTO.getValue(),
                        partyIdentityDTO.getValidFromDt(),
                        partyIdentityDTO.getValidToDt());

                Optional<GenIdentityType> genIdentityTypeOpt = identityTypeRepository.findById(partyIdentityDTO.getIdentityTypeId());

                genIdentityTypeOpt.ifPresent(genPartyIdentity::setGenIdentityType);

                identityTypeRepository.save(genIdentityTypeOpt.orElseThrow());

                savedRole.addPartyIdentity(genPartyIdentity);
                genPartyIdentity.setGenPartyRole(savedRole);

                genPartyRoleRepository.save(savedRole);

                return genPartyIdentityRepository.save(genPartyIdentity);
            } else {
                logger.error("GenPartyRole not found for ID: {}", genPartyRoleId);
                return null; // or throw an exception
            }
        } catch (Exception e) {
            logger.error("Error creating party Identity", e);
            return null;
        }
    }

}
