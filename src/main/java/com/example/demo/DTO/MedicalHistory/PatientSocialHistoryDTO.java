package com.example.demo.DTO.MedicalHistory;


import com.example.demo.Enumerators.MedicalHistory.SmokingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientSocialHistoryDTO {

    private Long patientSocialHxId;
    private Long patientId;
    private Long partyRoleTypeId;
    private Long encounterId;
    private Date recordedDate;
    private SmokingStatus smokingStatusCd;
    private int tobaccoPakPerDay;
    private int tobaccoUserYears;
    private Date smokingQuitDate;
    private char cigarettesInd;
    private char pipesInd;
    private char cigarsInd;
    private char snuffInd;
    private char chewInd;
    private String comment;

    // Constructors, getters, and setters
}

