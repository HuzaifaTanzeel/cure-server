package com.example.demo.Service.AppointmentManagement.ServiceImpl;

import com.example.demo.CommonFramework.Model.Facility.GenFacility;
import com.example.demo.CommonFramework.Model.Facility.PractitionerFacilityAffiliation;
import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Repositories.GenPersonRepository;
import com.example.demo.CommonFramework.Services.Interface.Person.PersonServiceInterface;
import com.example.demo.DTO.AppointmentManagement.AppointmentDTO;
import com.example.demo.DTOMapper.AppointmentManagement.AppointmentMapper;
import com.example.demo.Enumerators.AppointmentManagement.AppointmentStatus;
import com.example.demo.Model.AppointmentManagement.Appointment;
import com.example.demo.Model.Scheduling.AvailabilitySchedule;
import com.example.demo.Model.Scheduling.SchedulableResource;
import com.example.demo.Repository.AppointmentManagement.AppointmentRepository;
import com.example.demo.Repository.Scheduling.ScheduleAvailabilityRepository;
import com.example.demo.Service.AppointmentManagement.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    private final ScheduleAvailabilityRepository scheduleAvailabilityRepository;

    private final AppointmentMapper appointmentMapper;

    private final PersonServiceInterface personService;

    private final GenPersonRepository genPersonRepository;



    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper, ScheduleAvailabilityRepository scheduleAvailabilityRepository, AppointmentMapper appointmentMapper, PersonServiceInterface personService, PersonServiceInterface personService1, GenPersonRepository genPersonRepository) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
        this.scheduleAvailabilityRepository = scheduleAvailabilityRepository;
        this.appointmentMapper = appointmentMapper;
        this.personService = personService1;
        this.genPersonRepository = genPersonRepository;
    }


    private Long getPractitionerIdForAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Get the availability schedule associated with the appointment
        AvailabilitySchedule availabilitySchedule = appointment.getAvailabilitySchedule();
        if (availabilitySchedule == null) {
            throw new RuntimeException("Availability schedule not found for the appointment");
        }

        // Get the schedulable resource associated with the availability schedule
        SchedulableResource resource = availabilitySchedule.getResource();
        if (resource == null) {
            throw new RuntimeException("Schedulable resource not found for the availability schedule");
        }

        // Get the practitioner facility affiliation associated with the resource
        List<PractitionerFacilityAffiliation> practitionerFacilityAffiliations = resource.getPractitionerFacilityAffiliationList();
        if (practitionerFacilityAffiliations.isEmpty()) {
            throw new RuntimeException("Practitioner facility affiliation not found for the resource");
        }

        // Assuming one practitioner is associated with the resource, you can return its ID
        PractitionerFacilityAffiliation practitionerFacilityAffiliation = practitionerFacilityAffiliations.get(0);
        return practitionerFacilityAffiliation.getAdmPractitioner().getPartyId().getPartyId();
    }


    private String extractFacilityName(Appointment appointment) {
        // Step 1: Access the availability schedule of the appointment
        AvailabilitySchedule availabilitySchedule = appointment.getAvailabilitySchedule();

        // Check if availability schedule is null
        if (availabilitySchedule == null) {
            return null;
        }

        // Step 2: Access the resource from the availability schedule
        SchedulableResource resource = availabilitySchedule.getResource();

        // Check if resource is null
        if (resource == null) {
            return null;
        }

        // Step 3: Access the list of practitioner facility affiliations from the resource
        List<PractitionerFacilityAffiliation> practitionerFacilityAffiliations = resource.getPractitionerFacilityAffiliationList();

        // Check if list of affiliations is empty
        if (practitionerFacilityAffiliations.isEmpty()) {
            return null;
        }

        // Assuming there is only one practitioner facility affiliation, you can directly access it
        PractitionerFacilityAffiliation practitionerFacilityAffiliation = practitionerFacilityAffiliations.get(0);

        // Step 4: Access the genFacility from the practitioner facility affiliation
        GenFacility facility = practitionerFacilityAffiliation.getGenFacility();

        // Finally, retrieve the facility name
        return facility != null ? facility.getName() : null;
    }

    @Transactional
    @Override
    public List<LocalTime[]> getAvailableTimeSlots(LocalDate appointmentDate, Long availabilityId) {
        // Retrieve availability slots for the specified availability ID
        AvailabilitySchedule availabilitySchedule = scheduleAvailabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new RuntimeException("Availability schedule not found"));

        LocalTime startTime = availabilitySchedule.getStartTime();
        int slotDuration = availabilitySchedule.getSlotDuration();

        // Generate time slots based on availability start time, slot duration, and number of slots
        List<LocalTime[]> timeSlots = new ArrayList<>();
        LocalTime currentTime = startTime;
        for (int i = 0; i < availabilitySchedule.getNumberOfSlots(); i++) {
            LocalTime endTime = currentTime.plusMinutes(slotDuration);
            timeSlots.add(new LocalTime[]{currentTime, endTime});
            currentTime = endTime;
        }

        // Retrieve booked appointments for the specified appointment date and availability ID
        List<Appointment> bookedAppointments = appointmentRepository.findByAppointmentDateAndAvailabilityId(appointmentDate, availabilityId);

        // Filter out the booked time slots
        for (Appointment appointment : bookedAppointments) {
            timeSlots.removeIf(slot -> slot[0].equals(appointment.getAppointmentStartTime()) && slot[1].equals(appointment.getAppointmentEndTime()));
        }

        return timeSlots;
    }

    @Transactional
    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        // Map DTO to entity
        Appointment appointment = appointmentMapper.dtoToEntity(appointmentDTO);
        Appointment savedAppointment=appointmentRepository.save(appointment);

        Optional<AvailabilitySchedule> availabilityScheduleOptional=scheduleAvailabilityRepository.findById(appointmentDTO.getAvailabilityId());
        AvailabilitySchedule availabilitySchedule=availabilityScheduleOptional.get();
        availabilitySchedule.addAppointment(savedAppointment);

        GenPerson patientDetails=genPersonRepository.findByPartyId(appointment.getPatientId());
        Long practitionerId=getPractitionerIdForAppointment(savedAppointment.getAppointmentId());
        GenPerson practitionerDetails=genPersonRepository.findByPartyId(practitionerId);



        String patientName= patientDetails.getFirstName() + patientDetails.getLastName();
        String practitionerName=practitionerDetails.getFirstName()+practitionerDetails.getLastName();
        String clinicName=extractFacilityName(savedAppointment);

        AppointmentDTO responseDTO=appointmentMapper.entityToDto(savedAppointment);
        responseDTO.setPractitionerName(practitionerName);
        responseDTO.setPatientName(patientName);
        responseDTO.setClinicName(clinicName);
        // Map entity back to DTO
        return responseDTO;
    }

    @Transactional
    @Override
    public List<AppointmentDTO> getAppointmentsByResourceAndDate(Long resourceId, LocalDate appointmentDate, AppointmentStatus appointmentStatus) {
        List<Appointment> appointmentList = appointmentRepository.findByResourceIdAndAppointmentDate(resourceId, appointmentDate, appointmentStatus);

        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        for (Appointment savedAppointment : appointmentList) {
            GenPerson patientDetails=genPersonRepository.findByPartyId(savedAppointment.getPatientId());
            Long practitionerId=getPractitionerIdForAppointment(savedAppointment.getAppointmentId());
            GenPerson practitionerDetails=genPersonRepository.findByPartyId(practitionerId);



            String patientName= patientDetails.getFirstName() + patientDetails.getLastName();
            String practitionerName=practitionerDetails.getFirstName()+practitionerDetails.getLastName();
            String clinicName=extractFacilityName(savedAppointment);

            AppointmentDTO responseDTO=appointmentMapper.entityToDto(savedAppointment);
            responseDTO.setPractitionerName(practitionerName);
            responseDTO.setPatientName(patientName);
            responseDTO.setClinicName(clinicName);
            appointmentDTOList.add(responseDTO);
        }

        return appointmentDTOList;
    }

    public void cancelAppointment(Long appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setAppointmentStatusCd(AppointmentStatus.CANCELLED); // Assuming AppointmentStatus is an enum
            appointmentRepository.save(appointment);
        } else {
            throw new EntityNotFoundException("Appointment not found with id: " + appointmentId);
        }
    }





}
