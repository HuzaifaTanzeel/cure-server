package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.ContactPointDTO;
import com.example.demo.CommonFramework.Model.Contact.GenContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPointId;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenContactPointRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenPartyContatctPointRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class ContactPointDTOMapper implements Function<GenPartyContactPoint, ContactPointDTO> {

    @Autowired
    private GenPartyRoleRepository genPartyRoleRepository;

    @Autowired
    private GenContactPointRepository genContactPointRepository;

    @Autowired
    private GenPartyContatctPointRepository genPartyContatctPointRepository;

    private static final Logger logger = LoggerFactory.getLogger(AddressDTOMapper.class);



    @Override
    public ContactPointDTO apply(GenPartyContactPoint genPartyContactPoint) {
        return new ContactPointDTO(
                genPartyContactPoint.getGenPartyContactPointId().getGenPartyRole().getPartyId(),
                genPartyContactPoint.getGenPartyContactPointId().getGenPartyRole().getPartyRoleTypeId(),
                genPartyContactPoint.getContactPoint().getContactPointId(),
                genPartyContactPoint.getContactPoint().getCountryCode(),
                genPartyContactPoint.getContactPoint().getContactNumber(),
                genPartyContactPoint.getContactPoint().getContactPointTypeCd()
        );
    }


    @Transactional
    public GenPartyContactPoint mapToContactPoint(ContactPointDTO contactPointDTO) {
        try {
            System.out.println(contactPointDTO.getPartyId() +"  "+ contactPointDTO.getPartyRoleTypeId());
            GenPartyRoleId genPartyRoleId = new GenPartyRoleId(contactPointDTO.getPartyId(),contactPointDTO.getPartyRoleTypeId());
            Optional<GenPartyRole> genPartyRole=genPartyRoleRepository.findById(genPartyRoleId);
            if(genPartyRole.isPresent()) {
                GenPartyRole savedRole = genPartyRole.get();

                //Creating address entry
                GenContactPoint genContactPoint = new GenContactPoint(
                        contactPointDTO.getCountryCode(),
                        contactPointDTO.getContactNumber(),
                        contactPointDTO.getContactPointTypeCd()
                );

                //Saving address entry
                GenContactPoint savedContactPoint = genContactPointRepository.save(genContactPoint);



                //Linking address with Party & role
                GenPartyContactPoint genPartyContactPoint = new GenPartyContactPoint(
                        new GenPartyContactPointId(
                                genPartyRoleId,
                                savedContactPoint.getContactPointId()
                        )
                );
                genPartyContactPoint.setContactPoint(savedContactPoint);
                savedRole.addPartyContactPoint(genPartyContactPoint);
                genPartyRoleRepository.save(savedRole);
                return genPartyContatctPointRepository.save(genPartyContactPoint);
            }
            else {
                logger.error("GenPartyRole not found for ID: {}", genPartyRoleId);
                return null; // or throw an exception
            }
            // Set other properties as needed


        } catch (Exception e) {
            logger.error("Error creating party contactPoint", e);
            return null;
        }
    }
}
