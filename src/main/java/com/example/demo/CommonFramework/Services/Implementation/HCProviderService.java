package com.example.demo.CommonFramework.Services.Implementation;

import com.example.demo.CommonFramework.DTO.HCProviderOrgDTO;
import com.example.demo.CommonFramework.Model.Practice.HealthcareProviderOrganization;
import com.example.demo.CommonFramework.Model.Practice.PractitionerHcProvORGReltn;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.PractitionerHcProvReltnRepo;
import com.example.demo.DTOMapper.HCProviderDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HCProviderService {

    @Autowired
    private final HCProviderDTOMapper hcProviderDTOMapper;

    @Autowired
    private PractitionerHcProvReltnRepo practitionerHcProvReltnRepo;

    public PractitionerHcProvORGReltn getRecordByHcProvOrgPid(Long hcProvOrgPid) {
        return practitionerHcProvReltnRepo.findByPractitionerHcProvORGReltnIdHcProvOrgPid(hcProvOrgPid);
    }

    public HCProviderService(HCProviderDTOMapper hcProviderDTOMapper) {
        this.hcProviderDTOMapper = hcProviderDTOMapper;
    }

    @Transactional
    public HCProviderOrgDTO createPractice(HCProviderOrgDTO hcProviderOrgDTO){
        HealthcareProviderOrganization healthcareProviderOrganization = hcProviderDTOMapper.mapToHealthcareProvider(hcProviderOrgDTO);
        return hcProviderDTOMapper.apply(healthcareProviderOrganization);
    }

    @Transactional
    public HCProviderOrgDTO getPractice(Long partyId, Long partyRoleTypeId){
        PractitionerHcProvORGReltn savedPractitionerHcProvORGReltn=practitionerHcProvReltnRepo.findByPractitionerHcProvORGReltnIdPractitionerPidAndPractitionerHcProvORGReltnIdPartyRoleTypeId(partyId,partyRoleTypeId);
        HealthcareProviderOrganization healthcareProviderOrganization = savedPractitionerHcProvORGReltn.getHealthcareProviderOrganization();
        return hcProviderDTOMapper.apply(healthcareProviderOrganization);
    }
}
