package com.example.demo.DTOMapper;

import com.example.demo.CommonFramework.DTO.HCProviderOrgDTO;
import com.example.demo.CommonFramework.Model.*;
import com.example.demo.CommonFramework.Model.Practice.HealthcareProviderOrganization;
import com.example.demo.CommonFramework.Model.Practice.PractitionerHcProvORGReltn;
import com.example.demo.CommonFramework.Model.Practice.PractitionerHcProvORGReltnId;
import com.example.demo.CommonFramework.Repositories.AdmPractitionerRepository;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.HCProviderRepository;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.PractitionerHcProvReltnRepo;
import com.example.demo.DTOMapper.PersonalProfiling.AddressDTOMapper;
import com.example.demo.Model.Scheduling.Schedule;
import com.example.demo.Service.Scheduling.ScheduleService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class HCProviderDTOMapper implements Function<HealthcareProviderOrganization, HCProviderOrgDTO> {

    @Autowired
    AdmPractitionerRepository admPractitionerRepository;

    @Autowired
    private HCProviderRepository hcProviderRepository;

    @Autowired
    private PractitionerHcProvReltnRepo practitionerHcProvReltnRepo;

    @Autowired
    private ScheduleService scheduleService;

    private static final Logger logger = LoggerFactory.getLogger(AddressDTOMapper.class);

    @Transactional
    @Override
    public HCProviderOrgDTO apply(HealthcareProviderOrganization healthcareProviderOrganization) {

        try {
            PractitionerHcProvORGReltn practitionerHcProvORGReltn=practitionerHcProvReltnRepo.findByPractitionerHcProvORGReltnIdHcProvOrgPid(healthcareProviderOrganization.getPartyId());


                return new HCProviderOrgDTO(
                        practitionerHcProvORGReltn.getPractitionerHcProvORGReltnId().getPractitionerPid(),
                        practitionerHcProvORGReltn.getPractitionerHcProvORGReltnId().getPartyRoleTypeId(),
                        healthcareProviderOrganization.getPartyId(),
                        healthcareProviderOrganization.getPartyTypeCd(),
                        healthcareProviderOrganization.getProviderName(),
                        healthcareProviderOrganization.getOrgTypeCd(),
                        practitionerHcProvORGReltn.getPractitionerHcProvORGReltnId().getPracticeRoleCd()
                );

        }
        catch (Exception e) {
            logger.error("Error getting Practice", e);
            return null;
        }

    }

    @Transactional
    public HealthcareProviderOrganization mapToHealthcareProvider(HCProviderOrgDTO hcProviderOrgDTO){

        try{
            System.out.println("111");
            AdmPractitioner admPractitioner=admPractitionerRepository.findPractitionerByPartyIdAndRoleTypeId(
                    hcProviderOrgDTO.getPartyId(),
                    hcProviderOrgDTO.getPartyRoleTypeId()
            );
            System.out.println("222");
            HealthcareProviderOrganization healthcareProviderOrganization = new HealthcareProviderOrganization(
                    hcProviderOrgDTO.getPartyTypeCd(),
                    hcProviderOrgDTO.getOrgTypeCd(),
                    hcProviderOrgDTO.getProviderName()

            );
            HealthcareProviderOrganization savedHealthcareProviderOrganization=hcProviderRepository.save(healthcareProviderOrganization);
            Schedule savedSchedule =scheduleService.createSchedule(savedHealthcareProviderOrganization.getPartyId());
            savedHealthcareProviderOrganization.addSchedule(savedSchedule);

            PractitionerHcProvORGReltn practitionerHcProvORGReltn = new PractitionerHcProvORGReltn(
                    new PractitionerHcProvORGReltnId(
                            savedHealthcareProviderOrganization.getPartyId(),
                            hcProviderOrgDTO.getPartyId(),
                            hcProviderOrgDTO.getPartyRoleTypeId(),
                            hcProviderOrgDTO.getPracticeRoleCd()
                    ),
                    admPractitioner,
                    healthcareProviderOrganization
            );

            practitionerHcProvORGReltn.setHealthcareProviderOrganization(healthcareProviderOrganization);

            practitionerHcProvORGReltn.setAdmPractitioner(admPractitioner);

            healthcareProviderOrganization.addPractitionerHcProvORGReltn(practitionerHcProvORGReltn);

            admPractitioner.addPractitionerHcProvORGReltn(practitionerHcProvORGReltn);


            admPractitionerRepository.save(admPractitioner);

            practitionerHcProvReltnRepo.save(practitionerHcProvORGReltn);

            return hcProviderRepository.save(healthcareProviderOrganization);
        }catch (Exception e) {
            logger.error("Error creating Practice", e);
            return null;
        }






    }
}
