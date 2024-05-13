package com.example.demo.DTO.Medication;

import com.example.demo.DTO.Diagnosis.ClnPatientDiagnosisDTO;
import com.example.demo.Enumerators.Diagnosis.ReqPriority;
import com.example.demo.Enumerators.Medication.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationRequestDTO {

    private Long medReqId;
    private Long patientId;
    private Long partyRoleTypeId;
    private DrugDTO drug;
    private String medicationName;
    private Long encounterId;
    private MedReqCategory requestCd;
    private Route routeCd;
    private DrugForm doseFormCd;
    private Double dosageQuantity;
    private MedFrequency frequencyCd;
    private Double daysSupply;
    private Double totalQuantity;
    private Character asNeededFlag;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReqPriority priorityCd;
    private LocalDateTime requestDtTm;
    private MedReqStatus statusCd;
    private MedReqIntent intentCd;
    private String groupCode;
    private String patientInstruction;
    private String comment;
    private Long requestedByPid;
    private Long recordedByPid;
    private List<ClnPatientDiagnosisDTO> clnPatientDiagnosisList=new ArrayList<>();

}

