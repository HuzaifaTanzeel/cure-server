package com.example.demo.DTO.MedicalHistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientSurgicalHistoryDTO {

    private Long patientSurgicalHxId;
    private Long patientId;
    private Long partyRoleTypeId;
    private Long encounterId;
//    private Long procedureId;
//    private String surgeryName;
    private Date recordedDate;
    private Date surgeryDate;
    private String comment;
    private ClinicalProcedureDTO clinicalProcedure;
}
