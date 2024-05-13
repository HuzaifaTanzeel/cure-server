package com.example.demo.CommonFramework.Services.Implementation;

import com.example.demo.CommonFramework.DTO.FacilityDTO;
import com.example.demo.CommonFramework.Model.Facility.GenFacility;
import com.example.demo.CommonFramework.Model.Facility.PractitionerFacilityAffiliation;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.GenFacilityRepository;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.PractitionerFacilityAffiliationRepo;
import com.example.demo.DTOMapper.FacilityDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacilityService {


    @Autowired
    private final FacilityDTOMapper facilityDTOMapper;

    @Autowired
    private final PractitionerFacilityAffiliationRepo practitionerFacilityAffiliationRepo;

    @Autowired
    private final GenFacilityRepository genFacilityRepository;

    public FacilityService(FacilityDTOMapper facilityDTOMapper, PractitionerFacilityAffiliationRepo practitionerFacilityAffiliationRepo, GenFacilityRepository genFacilityRepository) {
        this.facilityDTOMapper = facilityDTOMapper;
        this.practitionerFacilityAffiliationRepo = practitionerFacilityAffiliationRepo;
        this.genFacilityRepository = genFacilityRepository;
    }

    @Transactional
    public FacilityDTO createFacility(FacilityDTO facilityDTO){
        GenFacility savedFacility = facilityDTOMapper.mapToGenFacility(facilityDTO);
        return facilityDTOMapper.apply(savedFacility);
    }

    @Transactional
    public List<FacilityDTO> getFacility(Long partyId, Long partyRoleTypeId){
                 return practitionerFacilityAffiliationRepo.findByPractitionerFacilityAffiliationId_PractitionerPidAndPractitionerFacilityAffiliationId_PartyRoleTypeId(partyId,partyRoleTypeId)
                .stream()
                .map(PractitionerFacilityAffiliation::getGenFacility)
                .map(facilityDTOMapper).collect(Collectors.toList());

    }

    @Transactional
    public FacilityDTO getFacilityById(Long facilityId) {
        Optional<GenFacility> savedFacility = genFacilityRepository.findById(facilityId);
        return savedFacility.map(facilityDTOMapper).orElse(null);
    }


    //@Transactional
    public void deleteFacility(Long facilityId) {
        GenFacility facility = genFacilityRepository.findById(facilityId).orElseThrow(() -> new RuntimeException("Facility not found"));
        if (facility != null) {
            genFacilityRepository.delete(facility);
        }
    }
}
