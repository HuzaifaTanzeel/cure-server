package com.example.demo.Service.Scheduling;

import com.example.demo.DTO.Scheduling.AvailabilityScheduleDTO;
import com.example.demo.Enumerators.Scheduling.DayOfWeek;
import com.example.demo.Model.Scheduling.AvailabilitySchedule;

import java.util.List;

public interface AvailabilityScheduleService {

    public AvailabilityScheduleDTO addAvailabilitySchedule(AvailabilityScheduleDTO availabilityScheduleDTO);

    public void deleteAvailabilitySchedule(Long id);

    public List<AvailabilityScheduleDTO> getAvailabilitiesForResource(Long resourceId);

    public List<AvailabilityScheduleDTO> getAvailabilitiesForResourceByDay(Long resourceId, DayOfWeek weekDayCode );

    public List<AvailabilityScheduleDTO> getAvailabilitySchedulesByOrgId(Long orgId);
}


