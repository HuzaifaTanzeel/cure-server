package com.example.demo.Service.AppointmentManagement.ServiceImpl;

import com.example.demo.DTO.AppointmentManagement.EncounterDTO;
import com.example.demo.DTOMapper.AppointmentManagement.EncounterMapper;
import com.example.demo.Model.AppointmentManagement.Appointment;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Repository.AppointmentManagement.AppointmentRepository;
import com.example.demo.Repository.AppointmentManagement.EncounterRepository;
import com.example.demo.Service.AppointmentManagement.EncounterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EncounterServiceImpl implements EncounterService {

    private final EncounterMapper encounterMapper;
    private final EncounterRepository encounterRepository;

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public EncounterServiceImpl(EncounterMapper encounterMapper, EncounterRepository encounterRepository, AppointmentRepository appointmentRepository) {
        this.encounterMapper = encounterMapper;
        this.encounterRepository = encounterRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    @Override
    public EncounterDTO createEncounter(EncounterDTO encounterDTO) {
        // Retrieve the appointment
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(encounterDTO.getAppointmentId());
        if (optionalAppointment.isEmpty()) {
            // Handle the case when the appointment is not found
            throw new RuntimeException("Appointment with ID " + encounterDTO.getAppointmentId() + " not found");
        }

        // Map DTO to entity
        Appointment appointment = optionalAppointment.get();
        Encounter encounter = encounterMapper.dtoToEntity(encounterDTO);

        // Associate the encounter with the appointment
        encounter.setAppointment(appointment);

        // Save the encounter
        Encounter savedEncounter = encounterRepository.save(encounter);

        // Optionally, you can update the appointment's encounters list
        appointment.addEncounter(savedEncounter);
        appointmentRepository.save(appointment);

        // Return the DTO representation of the saved encounter
        return encounterMapper.entityToDto(savedEncounter);
    }


}


