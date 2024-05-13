package com.example.demo.Repository.AppointmentManagement;

import com.example.demo.Enumerators.AppointmentManagement.AppointmentStatus;
import com.example.demo.Model.AppointmentManagement.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> findByAppointmentDateAndAvailabilityId(LocalDate appointmentDate, Long availabilityId);

    @Query("SELECT a FROM Appointment a JOIN a.availabilitySchedule avs WHERE avs.resourceId = :resourceId AND a.appointmentDate = :appointmentDate AND a.appointmentStatusCd = :appointmentStatus")
    List<Appointment> findByResourceIdAndAppointmentDate(@Param("resourceId") Long resourceId, @Param("appointmentDate") LocalDate appointmentDate, @Param("appointmentStatus") AppointmentStatus appointmentStatus);


}
