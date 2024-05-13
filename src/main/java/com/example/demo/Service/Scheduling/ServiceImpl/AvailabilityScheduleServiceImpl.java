package com.example.demo.Service.Scheduling.ServiceImpl;

import com.example.demo.DTO.AppointmentManagement.AppointmentDTO;
import com.example.demo.DTO.Scheduling.AvailabilityScheduleDTO;
import com.example.demo.DTOMapper.AppointmentManagement.AppointmentMapper;
import com.example.demo.DTOMapper.Scheduling.AvailabilityScheduleMapper;
import com.example.demo.Enumerators.Scheduling.DayOfWeek;
import com.example.demo.Model.AppointmentManagement.Appointment;
import com.example.demo.Model.Scheduling.AvailabilitySchedule;
import com.example.demo.Model.Scheduling.SchedulableResource;
import com.example.demo.Model.Scheduling.Schedule;
import com.example.demo.Repository.Scheduling.ResourceRepository;
import com.example.demo.Repository.Scheduling.ScheduleAvailabilityRepository;
import com.example.demo.Repository.Scheduling.ScheduleRepository;
import com.example.demo.Service.Scheduling.AvailabilityScheduleService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AvailabilityScheduleServiceImpl implements AvailabilityScheduleService {


    private final ScheduleAvailabilityRepository scheduleAvailabilityRepository;
    private final ResourceRepository resourceRepository;
    private final ScheduleRepository scheduleRepository;
    private final AvailabilityScheduleMapper availabilityScheduleMapper;

    private final ModelMapper modelMapper;






    @Autowired
    public AvailabilityScheduleServiceImpl(ScheduleAvailabilityRepository scheduleAvailabilityRepository, ResourceRepository resourceRepository, ScheduleRepository scheduleRepository, AvailabilityScheduleMapper availabilityScheduleMapper, ModelMapper modelMapper, AppointmentMapper appointmentMapper) {
        this.scheduleAvailabilityRepository = scheduleAvailabilityRepository;
        this.resourceRepository = resourceRepository;
        this.scheduleRepository = scheduleRepository;
        this.availabilityScheduleMapper = availabilityScheduleMapper;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public AvailabilityScheduleDTO addAvailabilitySchedule(AvailabilityScheduleDTO availabilityScheduleDTO) {
        // Retrieve resource
        SchedulableResource resource = resourceRepository.findById(availabilityScheduleDTO.getResourceId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        // Retrieve schedule
        Schedule schedule = scheduleRepository.findById(availabilityScheduleDTO.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Schedule not found")) ;


        // Calculate end time
        LocalTime endTime = availabilityScheduleMapper.calculateEndTime(
                availabilityScheduleDTO.getStartTime(),
                availabilityScheduleDTO.getNumberOfSlots(),
                availabilityScheduleDTO.getSlotDuration()
        );
        // Set the calculated end time
        availabilityScheduleDTO.setEndTime(endTime);


        // Map DTO to entity
        AvailabilitySchedule availabilitySchedule = availabilityScheduleMapper.dtoToEntity(availabilityScheduleDTO);
        AvailabilitySchedule savedAvailabilitySchedule= scheduleAvailabilityRepository.save(availabilitySchedule);
        schedule.addAvailabilitySchedule(savedAvailabilitySchedule);
        resource.addAvailabilitySchedule(savedAvailabilitySchedule);

        resourceRepository.save(resource);
        scheduleRepository.save(schedule);

        return availabilityScheduleMapper.entityToDto(savedAvailabilitySchedule);
    }


    @Transactional
    @Override
    public void deleteAvailabilitySchedule(Long id) {
        // Find the social history record to delete
        AvailabilitySchedule existingRecord = scheduleAvailabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability Schedule not found"));

        // Delete the record
        scheduleAvailabilityRepository.delete(existingRecord);
    }


    @Transactional
    @Override
    public List<AvailabilityScheduleDTO> getAvailabilitiesForResource(Long resourceId) {
        List<AvailabilitySchedule> availabilitySchedules= scheduleAvailabilityRepository.findByResourceId(resourceId);

        return availabilitySchedules.stream()
                .map(availabilitySchedule -> modelMapper.map(availabilitySchedule, AvailabilityScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<AvailabilityScheduleDTO> getAvailabilitiesForResourceByDay(Long resourceId, DayOfWeek weekDayCode ) {
        List<AvailabilitySchedule> availabilitySchedules= scheduleAvailabilityRepository.findByResourceIdAndWeekDayCode(resourceId,weekDayCode);

        return availabilitySchedules.stream()
                .map(availabilitySchedule -> modelMapper.map(availabilitySchedule, AvailabilityScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<AvailabilityScheduleDTO> getAvailabilitySchedulesByOrgId(Long orgId) {
        List<AvailabilitySchedule> availabilitySchedules= scheduleAvailabilityRepository.findByOrgId(orgId);

        return availabilitySchedules.stream()
                .map(availabilitySchedule -> modelMapper.map(availabilitySchedule, AvailabilityScheduleDTO.class))
                .collect(Collectors.toList());

    }


}

