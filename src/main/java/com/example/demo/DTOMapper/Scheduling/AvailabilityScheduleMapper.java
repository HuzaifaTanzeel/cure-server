package com.example.demo.DTOMapper.Scheduling;

import com.example.demo.DTO.Diagnosis.ClnPatientDiagnosisDTO;
import com.example.demo.DTO.Scheduling.AvailabilityScheduleDTO;
import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
import com.example.demo.Model.Scheduling.AvailabilitySchedule;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AvailabilityScheduleMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public AvailabilityScheduleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;


        // Configure mappings: DTO to Entity
        modelMapper.createTypeMap(AvailabilityScheduleDTO.class, AvailabilitySchedule.class)
                .addMappings(mapping -> {
                    // Exclude auto-generated primary key
                    mapping.skip(AvailabilitySchedule::setAvailabilityId);
                });




    }


    @Transactional
    public AvailabilityScheduleDTO entityToDto(AvailabilitySchedule entity) {
        return modelMapper.map(entity, AvailabilityScheduleDTO.class);
    }

    @Transactional
    public AvailabilitySchedule dtoToEntity(AvailabilityScheduleDTO dto) {
        return modelMapper.map(dto, AvailabilitySchedule.class);
    }


    @Transactional
    public LocalTime calculateEndTime(LocalTime startTime, int numberOfSlots, int slotDuration) {
        // Calculate end time based on start time, number of slots, and slot duration
        int totalSlotDuration = numberOfSlots * slotDuration;
        int hours = totalSlotDuration / 60;
        int minutes = totalSlotDuration % 60;
        return startTime.plusHours(hours).plusMinutes(minutes);
    }
}
