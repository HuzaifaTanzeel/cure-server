package com.example.demo.DTOMapper.AppointmentManagement;

import com.example.demo.DTO.AppointmentManagement.AppointmentDTO;
import com.example.demo.DTO.AppointmentManagement.EncounterDTO;
import com.example.demo.Model.AppointmentManagement.Appointment;
import com.example.demo.Model.AppointmentManagement.Encounter;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    private final ModelMapper modelMapper;


    @Autowired
    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;


        modelMapper.createTypeMap(AppointmentDTO.class, Appointment.class)
                .addMappings(mapping -> {
                    // Exclude auto-generated primary key
                    mapping.skip(Appointment::setAppointmentId);
                });
        modelMapper.createTypeMap(Appointment.class, AppointmentDTO.class)
                .addMappings(mapping -> {
                    // Exclude auto-generated primary key
                    mapping.skip(AppointmentDTO::setPractitionerName);
                    mapping.skip(AppointmentDTO::setPatientName);
                    mapping.skip(AppointmentDTO::setClinicName);
                });
    }

    //@Transactional
    public AppointmentDTO entityToDto(Appointment entity) {
        return modelMapper.map(entity, AppointmentDTO.class);
    }

    //@Transactional
    public Appointment dtoToEntity(AppointmentDTO dto) {
        return modelMapper.map(dto, Appointment.class);
    }
}
