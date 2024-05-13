package com.example.demo.Service.AppointmentManagement;

import com.example.demo.DTO.AppointmentManagement.AppointmentDTO;
import com.example.demo.Enumerators.AppointmentManagement.AppointmentStatus;
import com.example.demo.Model.AppointmentManagement.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {

    public List<LocalTime[]> getAvailableTimeSlots(LocalDate appointmentDate, Long availabilityId);

    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);

    public List<AppointmentDTO> getAppointmentsByResourceAndDate(Long resourceId, LocalDate appointmentDate, AppointmentStatus appointmentStatus);

    public void cancelAppointment(Long appointmentId);

}
