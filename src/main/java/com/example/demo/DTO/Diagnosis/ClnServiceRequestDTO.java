package com.example.demo.DTO.Diagnosis;

import com.example.demo.DTO.MedicalHistory.ClinicalProcedureDTO;
import com.example.demo.Enumerators.Diagnosis.ReqPriority;
import com.example.demo.Enumerators.Diagnosis.RequestIntent;
import com.example.demo.Enumerators.Diagnosis.RequestStatus;
import com.example.demo.Enumerators.Diagnosis.ServReqCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClnServiceRequestDTO {

    private Long servReqId;
    private Long encounterId;
    private ClinicalProcedureDTO clinicalProcedure;
    private ServReqCategory categoryCode;
    private ReqPriority priorityCd;
    private RequestIntent intentCd;
    private RequestStatus statusCd;
    private String groupCode;
    private BigDecimal quantity;
    private LocalDate dueDateTime;
    private LocalDateTime authoredOnDateTime;
    private String requestedLocationName;
    private String supportingInfo;
    private String patientInstruction;
    private List<ClnPatientDiagnosisDTO> clnPatientDiagnosisList=new ArrayList<>();

}