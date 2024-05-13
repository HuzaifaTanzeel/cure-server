package com.example.demo.CommonFramework.Controllers;


import com.example.demo.DTO.AppointmentManagement.AppointmentDTO;
import com.example.demo.DTO.AppointmentManagement.EncounterDTO;
import com.example.demo.DTO.Diagnosis.ClnPatientDiagnosisDTO;
import com.example.demo.DTO.Diagnosis.ClnServiceRequestDTO;
import com.example.demo.DTO.MedicalHistory.AllergenDTO;
import com.example.demo.DTO.MedicalHistory.ClinicalProcedureDTO;
import com.example.demo.DTO.MedicalHistory.ClnConditionDTO;
import com.example.demo.DTO.Medication.DrugDTO;
import com.example.demo.DTO.Medication.MedicationRequestDTO;
import com.example.demo.DTO.Scheduling.AvailabilityScheduleDTO;
import com.example.demo.DTOMapper.Medication.DrugMapper;
import com.example.demo.Enumerators.AppointmentManagement.AppointmentStatus;
import com.example.demo.Enumerators.Scheduling.DayOfWeek;
import com.example.demo.Model.Medication.Drug;
import com.example.demo.Model.Observation.DgnLoinc;
import com.example.demo.Model.Observation.DgnLoincPanel;
import com.example.demo.Repository.MedicalHistory.ClinicalProcedureRepository;
import com.example.demo.Repository.Medication.DrugRepository;
import com.example.demo.Repository.Observation.DgnLoincRepository;
import com.example.demo.Service.AppointmentManagement.AppointmentService;
import com.example.demo.Service.AppointmentManagement.EncounterService;
import com.example.demo.Service.Diagnosis.PatientDiagnosisService;
import com.example.demo.Service.MedicalHistory.AllergenServiceI;
import com.example.demo.Service.MedicalHistory.MedHistoryService;
import com.example.demo.Service.MedicalHistory.SurgicalHistoryService;
import com.example.demo.Service.Medication.DrugService;
import com.example.demo.Service.Medication.PatientMedicationService;
import com.example.demo.Service.Observation.DgnLoincService;
import com.example.demo.Service.Scheduling.AvailabilityScheduleService;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@PersistenceContext
@RestController
@RequestMapping("/api/Encounter")
public class EncounterController {

    private final PatientDiagnosisService patientDiagnosisService;

    private final DgnLoincRepository dgnLoincRepository;

    private final DgnLoincService dgnLoincService;

    private final PatientMedicationService patientMedicationService;

    private final DrugRepository drugRepository;

    private final DrugMapper drugMapper;

    private final DrugService drugService;

    private final ClinicalProcedureRepository clinicalProcedureRepository;

    private final SurgicalHistoryService surgicalHistoryService;

    private final AvailabilityScheduleService availabilityScheduleService;

    private final AppointmentService appointmentService;

    private final AllergenServiceI allergenServiceI;

    private final MedHistoryService medHistoryService;

    private final EncounterService encounterService;


    @Autowired
    public EncounterController(PatientDiagnosisService patientDiagnosisService, DgnLoincRepository dgnLoincRepository, DgnLoincService dgnLoincService, PatientMedicationService patientMedicationService, DrugRepository drugRepository, DrugMapper drugMapper, DrugService drugService, ClinicalProcedureRepository clinicalProcedureRepository, SurgicalHistoryService surgicalHistoryService, AvailabilityScheduleService availabilityScheduleService, AppointmentService appointmentService, AllergenServiceI allergenServiceI, MedHistoryService medHistoryService, EncounterService encounterService) {
        this.patientDiagnosisService = patientDiagnosisService;
        this.dgnLoincRepository = dgnLoincRepository;
        this.dgnLoincService = dgnLoincService;
        this.patientMedicationService = patientMedicationService;
        this.drugRepository = drugRepository;
        this.drugMapper = drugMapper;
        this.drugService = drugService;
        this.clinicalProcedureRepository = clinicalProcedureRepository;
        this.surgicalHistoryService = surgicalHistoryService;
        this.availabilityScheduleService = availabilityScheduleService;
        this.appointmentService = appointmentService;
        this.allergenServiceI = allergenServiceI;
        this.medHistoryService = medHistoryService;
        this.encounterService = encounterService;
    }


    @GetMapping("/proceduresByKeyword/{keyWord}")
    public ResponseEntity<List<ClinicalProcedureDTO>> getProceduresByKeyword(@PathVariable String keyWord) {
        try {
            List<ClinicalProcedureDTO> clinicalProcedureDTOS = surgicalHistoryService.getProceduresByKeyword(keyWord);
            return new ResponseEntity<>(clinicalProcedureDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllergensByKeyword/{keyWord}")
    public ResponseEntity<List<AllergenDTO>> getAllergensByKeyword(@PathVariable String keyWord) {
        try {
            List<AllergenDTO> allergenDTOS = allergenServiceI.getAllergensByKeyword(keyWord);
            return new ResponseEntity<>(allergenDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getConditionsByKeyword/{keyWord}")
    public ResponseEntity<List<ClnConditionDTO>> getConditionsByKeyword(@PathVariable String keyWord) {
        try {
            List<ClnConditionDTO> conditionDTOS = medHistoryService.getConditionsByKeyword(keyWord);
            return new ResponseEntity<>(conditionDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addDiagnosis")
    public ResponseEntity<ClnPatientDiagnosisDTO> addDiagnosis(@RequestBody ClnPatientDiagnosisDTO clnPatientDiagnosisDTO) {
        try {
            ClnPatientDiagnosisDTO diagnosisDTOResponse = patientDiagnosisService.addPatientDiagnosis(clnPatientDiagnosisDTO);
            return new ResponseEntity<>(diagnosisDTOResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPatientDiagnosisByEncounter/{encounterId}")
    public ResponseEntity<List<ClnPatientDiagnosisDTO>> getPatientDiagnosisByEncounter(@PathVariable Long encounterId) {
        try {
            List<ClnPatientDiagnosisDTO> clnPatientDiagnosisDTOS = patientDiagnosisService.getAllPatientDiagnosisByEncounter(encounterId);
            return new ResponseEntity<>(clnPatientDiagnosisDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping("/addServiceRequest")
    public ResponseEntity<ClnServiceRequestDTO> addServiceRequest(@RequestBody ClnServiceRequestDTO clnServiceRequestDTO) {
        try {
            ClnServiceRequestDTO serviceRequestDTOResponse = patientDiagnosisService.addServiceRequest(clnServiceRequestDTO);
            return new ResponseEntity<>(serviceRequestDTOResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getServiceRequest/{serviceRequestId}")
    public ResponseEntity<ClnServiceRequestDTO> getServiceRequest(@PathVariable Long serviceRequestId) {
        try {
            ClnServiceRequestDTO clnServiceRequestDTO = patientDiagnosisService.getServiceRequestsById(serviceRequestId);
            return new ResponseEntity<>(clnServiceRequestDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPatientServiceRequestsByEncounter/{encounterId}")
    public ResponseEntity<List<ClnServiceRequestDTO>> getServiceRequestsByEncounter(@PathVariable Long encounterId) {
        try {
            List<ClnServiceRequestDTO> clnServiceRequestDTOS = patientDiagnosisService.getServiceRequestsByEncounter(encounterId);
            return new ResponseEntity<>(clnServiceRequestDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addMedicationRequest")
    public ResponseEntity<MedicationRequestDTO> addMedicationRequest(@RequestBody MedicationRequestDTO medicationRequestDTO) {
        try {
            MedicationRequestDTO medicationRequestDTOResponse = patientMedicationService.addMedicationRequest(medicationRequestDTO);
            return new ResponseEntity<>(medicationRequestDTOResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/getMedicationRequest/{medRequestId}")
    public ResponseEntity<MedicationRequestDTO> getMedicationRequest(@PathVariable Long medRequestId) {
        try {
            MedicationRequestDTO medicationRequestDTO = patientMedicationService.getMedicationRequestsById(medRequestId);
            return new ResponseEntity<>(medicationRequestDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPatientMedicationRequestsByEncounter/{encounterId}")
    public ResponseEntity<List<MedicationRequestDTO>> getMedicationRequestsByEncounter(@PathVariable Long encounterId) {
        try {
            List<MedicationRequestDTO> medicationRequestDTOS = patientMedicationService.getMedicationRequestsByEncounter(encounterId);
            return new ResponseEntity<>(medicationRequestDTOS, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }






    @GetMapping("/getObs/{loincNum}")
    public ResponseEntity<DgnLoinc> getObservationList(@PathVariable  String loincNum) {
        try {
            DgnLoinc dgnLoinc=dgnLoincRepository.findByLoincNum(loincNum);
            return new ResponseEntity<>(dgnLoinc, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-all-with-panels")
    public ResponseEntity<List<DgnLoinc>> fetchAllLoincWithPanels() {
        List<DgnLoinc> loincList = dgnLoincService.fetchAllLoincWithPanels();
        return ResponseEntity.ok(loincList);
    }

    @GetMapping("/fetchLoincPanel/{loincNum}")
    public ResponseEntity<DgnLoincPanel> fetchAllLoincWithPanels(@PathVariable String loincNum) {
        DgnLoincPanel dgnLoincPanel = dgnLoincService.fetchLoincPanel(loincNum);
        return ResponseEntity.ok(dgnLoincPanel);
    }


    @GetMapping("/getDrug/{id}")
    public ResponseEntity<DrugDTO> getDrug(@PathVariable Long id) {
        try {
            Optional<Drug> drug=drugRepository.findById(id);
            DrugDTO drugDTO=drugMapper.entityToDto(drug.get());

            return new ResponseEntity<>(drugDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDrugByKeyword/{keyword}")
    public ResponseEntity<List<DrugDTO>> getDrugByKeyword(@PathVariable String keyword) {
        try {
            List<DrugDTO> drugDTOS=drugService.getDrugsByKeyword(keyword);
            return new ResponseEntity<>(drugDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addAvailabilitySchedule")
    public ResponseEntity<AvailabilityScheduleDTO> addAvailabilitySchedule(@RequestBody AvailabilityScheduleDTO availabilityScheduleDTO) {
        try {
            AvailabilityScheduleDTO availabilityScheduleDTOResponse = availabilityScheduleService.addAvailabilitySchedule(availabilityScheduleDTO);
            return new ResponseEntity<>(availabilityScheduleDTOResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteAvailabilitySchedule/{id}")
    public ResponseEntity<?> deleteAvailabilitySchedule(@PathVariable Long id) {
        try {
            availabilityScheduleService.deleteAvailabilitySchedule(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete AvailabilitySchedule: " + e.getMessage());
        }
    }

    @GetMapping("/getAvailabilitiesForResource/{id}")
    public ResponseEntity<List<AvailabilityScheduleDTO>> getAvailabilitiesForResource(@PathVariable Long id) {
        try {
            List<AvailabilityScheduleDTO> availabilityScheduleDTOS = availabilityScheduleService.getAvailabilitiesForResource(id);
            return new ResponseEntity<>(availabilityScheduleDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAvailabilitiesForResourceByDay/{id}/{dayOfWeek}")
    public ResponseEntity<List<AvailabilityScheduleDTO>> getAvailabilitiesForResourceByDay(@PathVariable Long id, @PathVariable DayOfWeek dayOfWeek) {
        try {
            List<AvailabilityScheduleDTO> availabilityScheduleDTOS = availabilityScheduleService.getAvailabilitiesForResourceByDay(id,dayOfWeek);
            return new ResponseEntity<>(availabilityScheduleDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAvailabilitiesForPractice/{id}")
    public ResponseEntity<List<AvailabilityScheduleDTO>> getAvailabilitiesForPractice(@PathVariable Long id) {
        try {
            List<AvailabilityScheduleDTO> availabilityScheduleDTOS = availabilityScheduleService.getAvailabilitySchedulesByOrgId(id);
            return new ResponseEntity<>(availabilityScheduleDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAvailableTimeSlots")
    public ResponseEntity<List<LocalTime[]>> getAvailableTimeSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
            @RequestParam Long availabilityId) {

        List<LocalTime[]> availableTimeSlots = appointmentService.getAvailableTimeSlots(appointmentDate, availabilityId);
        return new ResponseEntity<>(availableTimeSlots, HttpStatus.OK);
    }

    @PostMapping("/createAppointment")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            AppointmentDTO appointmentDTOResponse = appointmentService.createAppointment(appointmentDTO);
            return new ResponseEntity<>(appointmentDTOResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createEncounter")
    public ResponseEntity<EncounterDTO> createEncounter(@RequestBody EncounterDTO encounterDTO) {
        try {
            EncounterDTO encounterDTOResponse = encounterService.createEncounter(encounterDTO);
            return new ResponseEntity<>(encounterDTOResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAppointmentsByDate")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDate(
            @RequestParam("resourceId") Long resourceId,
            @RequestParam("appointmentDate") LocalDate appointmentDate,
            @RequestParam("appointmentStatus") AppointmentStatus appointmentStatus) {

        List<AppointmentDTO> appointmentDTOS = appointmentService.getAppointmentsByResourceAndDate(resourceId, appointmentDate, appointmentStatus);
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }


    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelAppointment(@PathVariable("id") Long appointmentId) {
        try {
            appointmentService.cancelAppointment(appointmentId);
            return ResponseEntity.ok("Appointment cancelled successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel appointment: " + e.getMessage());
        }
    }
























}
