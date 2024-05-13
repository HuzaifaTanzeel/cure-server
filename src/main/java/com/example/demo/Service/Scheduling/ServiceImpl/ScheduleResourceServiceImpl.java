package com.example.demo.Service.Scheduling.ServiceImpl;

import com.example.demo.CommonFramework.Model.Facility.PractitionerFacilityAffiliation;
import com.example.demo.CommonFramework.Repositories.PracticeANDFacility.PractitionerFacilityAffiliationRepo;
import com.example.demo.DTO.Scheduling.ScheduleResourceDTO;
import com.example.demo.Model.Scheduling.SchedulableResource;
import com.example.demo.Model.Scheduling.Schedule;
import com.example.demo.Model.Scheduling.ScheduleResource;
import com.example.demo.Repository.Scheduling.ResourceRepository;
import com.example.demo.Repository.Scheduling.ScheduleRepository;
import com.example.demo.Repository.Scheduling.ScheduleResourceRepository;
import com.example.demo.Service.Scheduling.ScheduleResourceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleResourceServiceImpl implements ScheduleResourceService {

    private final ScheduleRepository scheduleRepository;

    private final ResourceRepository resourceRepository;

    private final PractitionerFacilityAffiliationRepo practitionerFacilityAffiliationRepo;

    private final ScheduleResourceRepository scheduleResourceRepository;

    @Autowired
    public ScheduleResourceServiceImpl(ScheduleRepository scheduleRepository, ResourceRepository resourceRepository, PractitionerFacilityAffiliationRepo practitionerFacilityAffiliationRepo, ScheduleResourceRepository scheduleResourceRepository) {
        this.scheduleRepository = scheduleRepository;
        this.resourceRepository = resourceRepository;
        this.practitionerFacilityAffiliationRepo = practitionerFacilityAffiliationRepo;
        this.scheduleResourceRepository = scheduleResourceRepository;
    }

    @Transactional
    public void associateScheduleResource(ScheduleResourceDTO scheduleResourceDTO) {
        // Find PractitionerFacilityAffiliation
        PractitionerFacilityAffiliation practitionerFacilityAffiliation = practitionerFacilityAffiliationRepo
                .findByPractitionerFacilityAffiliationIdFacilityIdAndPractitionerFacilityAffiliationIdPractitionerPidAndPractitionerFacilityAffiliationIdPartyRoleTypeId(
                        scheduleResourceDTO.getFacilityId(),
                        scheduleResourceDTO.getPractitionerPid(),
                        scheduleResourceDTO.getPartyRoleTypeId());

        if (practitionerFacilityAffiliation == null) {
            // Handle error: No practitionerFacilityAffiliation found
            return;
        }

        // Find Schedule
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleResourceDTO.getScheduleId());
        if (optionalSchedule.isEmpty()) {
            // Handle error: No schedule found
            return;
        }
        Schedule schedule = optionalSchedule.get();

        // Find SchedulableResource
        Long resourceId = practitionerFacilityAffiliation.getResourceId();
        Optional<SchedulableResource> optionalSchedulableResource = resourceRepository.findById(resourceId);
        if (optionalSchedulableResource.isEmpty()) {
            // Handle error: No SchedulableResource found
            return;
        }
        SchedulableResource schedulableResource = optionalSchedulableResource.get();

        // Create ScheduleResource
        ScheduleResource scheduleResource = new ScheduleResource(schedule, schedulableResource);
        ScheduleResource savedScheduleResource = scheduleResourceRepository.save(scheduleResource);

        // Update associations
        schedulableResource.addScheduleResource(savedScheduleResource);
        schedule.addScheduleResource(savedScheduleResource);

        // Save changes
        scheduleRepository.save(schedule);
        resourceRepository.save(schedulableResource);
        scheduleResourceRepository.save(savedScheduleResource);
    }
}
