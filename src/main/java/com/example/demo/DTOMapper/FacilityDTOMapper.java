package com.example.demo.DTOMapper;

import com.example.demo.CommonFramework.DTO.FacilityDTO;
import com.example.demo.CommonFramework.Model.Address.GenAddress;
import com.example.demo.CommonFramework.Model.AdmPractitioner;
import com.example.demo.CommonFramework.Model.Contact.GenContactPoint;
import com.example.demo.CommonFramework.Model.Facility.*;
import com.example.demo.CommonFramework.Model.Practice.GenOrganization;
import com.example.demo.CommonFramework.Repositories.*;
import com.example.demo.CommonFramework.Repositories.Address.GenAddressRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenContactPointRepository;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.GenFacilityAddressRepository;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.GenFacilityContactRepository;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.GenFacilityRepository;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.PractitionerFacilityAffiliationRepo;
import com.example.demo.DTOMapper.PersonalProfiling.AddressDTOMapper;
import com.example.demo.Model.Scheduling.SchedulableResource;
import com.example.demo.Repository.Scheduling.ResourceRepository;
import com.example.demo.Service.Scheduling.ResourceService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class FacilityDTOMapper implements Function<GenFacility, FacilityDTO> {


    @Autowired
    private GenOrganizationRepository genOrganizationRepository;

    @Autowired
    private GenFacilityRepository genFacilityRepository;

    @Autowired
    private GenContactPointRepository genContactPointRepository;

    @Autowired
    private GenAddressRepository genAddressRepository;

    @Autowired
    private GenFacilityAddressRepository genFacilityAddressRepository;

    @Autowired
    private GenFacilityContactRepository genFacilityContactRepository;

    @Autowired
    private AdmPractitionerRepository admPractitionerRepository;

    @Autowired
    private PractitionerFacilityAffiliationRepo practitionerFacilityAffiliationRepo;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRepository resourceRepository;


    private static final Logger logger = LoggerFactory.getLogger(AddressDTOMapper.class);


    @Transactional
    @Override
    public FacilityDTO apply(GenFacility genFacility) {


        Optional<PractitionerFacilityAffiliation> practitionerFacilityAffiliation=practitionerFacilityAffiliationRepo.findByPractitionerFacilityAffiliationId_FacilityId(genFacility.getFacilityId());
        if(practitionerFacilityAffiliation.isPresent())
        {
            PractitionerFacilityAffiliation savedPractitionerFacilityAffiliation=practitionerFacilityAffiliation.get();

            return new FacilityDTO(
                    savedPractitionerFacilityAffiliation.getPractitionerFacilityAffiliationId().getPractitionerPid(),
                    savedPractitionerFacilityAffiliation.getPractitionerFacilityAffiliationId().getPartyRoleTypeId(),
                    genFacility.getOrgId(),
                    genFacility.getFacilityId(),
                    genFacility.getName(),
                    genFacility.getFacilityAddresses().get(0).getGenAddress().getAddressId(),
                    genFacility.getFacilityAddresses().get(0).getGenAddress().getAddrTypeCd(),
                    genFacility.getFacilityAddresses().get(0).getGenAddress().getAddrLine1(),
                    genFacility.getFacilityContactPoints().get(0).getGenContactPoint().getCountryCode(),
                    genFacility.getFacilityContactPoints().get(0).getGenContactPoint().getContactNumber(),
                    genFacility.getFacilityContactPoints().get(0).getGenContactPoint().getContactPointTypeCd(),
                    genFacility.getFacilityTypeCd(),
                    genFacility.getFacilityPhyTypeCd(),
                    genFacility.getFacilityStatusCd(),
                    genFacility.getFacilityOperationalStatusCd(),
                    savedPractitionerFacilityAffiliation.getPractitionerFacilityAffiliationId().getFacilityAffiliationRoleCd(),
                    savedPractitionerFacilityAffiliation.getResourceId()
            );

        }
        else {
            logger.error("Error creating PractitionerFacilityAffiliation" );
            return null;
        }


    }


    @Transactional
    public GenFacility mapToGenFacility(FacilityDTO facilityDTO) {
        // Implement conversion logic from FacilityDTO to GenFacility

        AdmPractitioner admPractitioner=admPractitionerRepository.findPractitionerByPartyIdAndRoleTypeId(
                facilityDTO.getPartyId(),
                facilityDTO.getPartyRoleTypeId()
        );

        GenFacility genFacility = new GenFacility(
                facilityDTO.getFacilityName(),
                facilityDTO.getFacilityTypeCd(),
                facilityDTO.getFacilityPhyTypeCd(),
                facilityDTO.getFacilityStatusCd(),
                facilityDTO.getFacilityOperationalStatusCd(),
                facilityDTO.getOrgId()
        );

        GenOrganization genOrganization=genOrganizationRepository.findById(facilityDTO.getOrgId()).get();
        genFacility.setOrganization(genOrganization);
        genOrganization.addFacility(genFacility);
        genOrganizationRepository.save(genOrganization);
        GenFacility savedFacility=genFacilityRepository.save(genFacility);

        SchedulableResource resource=resourceService.createPracFascilityResource();


        PractitionerFacilityAffiliation practitionerFacilityAffiliation = new PractitionerFacilityAffiliation(
                new PractitionerFacilityAffiliationId(
                        savedFacility.getFacilityId(),
                        facilityDTO.getPartyId(),
                        facilityDTO.getPartyRoleTypeId(),
                        facilityDTO.getFacilityAffiliationRoleCd()
                ),
                admPractitioner,
                savedFacility,
                resource

        );

        PractitionerFacilityAffiliation savedPractitionerFacilityAffiliation=practitionerFacilityAffiliationRepo.save(practitionerFacilityAffiliation);
        resource.addPractitionerFacilityAffiliation(savedPractitionerFacilityAffiliation);
        savedPractitionerFacilityAffiliation.setResourceId(resource.getResourceId());
        resourceRepository.save(resource);

        savedPractitionerFacilityAffiliation.setGenFacility(savedFacility);
        savedPractitionerFacilityAffiliation.setAdmPractitioner(admPractitioner);
        savedFacility.addPractitionerFacilityAffiliation(savedPractitionerFacilityAffiliation);
        admPractitioner.addPractitionerFacilityAffiliation(savedPractitionerFacilityAffiliation);
        admPractitionerRepository.save(admPractitioner);
        practitionerFacilityAffiliationRepo.save(savedPractitionerFacilityAffiliation);
        createFacilityAddress(facilityDTO,savedFacility);
        createFacilityContactPoint(facilityDTO,savedFacility);

        return genFacilityRepository.save(savedFacility);
    }

    @Transactional
    private void createFacilityAddress(FacilityDTO facilityDTO, GenFacility genFacility){
        GenAddress genAddress=new GenAddress(
                facilityDTO.getAddressId(),
                facilityDTO.getAddrTypeCd(),
                facilityDTO.getAddrLine1()
        );

        GenAddress savedGenAddress=genAddressRepository.save(genAddress);
        GenFacilityAddress savedFacilityAddress=new GenFacilityAddress(genFacility, savedGenAddress);

        genAddress.addFacilityAddress(savedFacilityAddress);
        genFacility.addFacilityAddress(savedFacilityAddress);

        genAddressRepository.save(genAddress);
        genFacilityAddressRepository.save(savedFacilityAddress);
    }

    @Transactional
    public void createFacilityContactPoint(FacilityDTO facilityDTO,GenFacility genFacility){
        GenContactPoint genContactPoint = new GenContactPoint(
                facilityDTO.getCountryCode(),
                facilityDTO.getContactNumber(),
                facilityDTO.getContactPointTypeCd()
        );
        GenContactPoint savedGenContactPoint=genContactPointRepository.save(genContactPoint);
        GenFacilityContactPoint savedFacilityContactPoint=new GenFacilityContactPoint(genFacility, savedGenContactPoint);

        genContactPoint.addFacilityContactPoint(savedFacilityContactPoint);
        genFacility.addFacilityContactPoint(savedFacilityContactPoint);

        genContactPointRepository.save(genContactPoint);
        genFacilityContactRepository.save(savedFacilityContactPoint);
    }
















}
