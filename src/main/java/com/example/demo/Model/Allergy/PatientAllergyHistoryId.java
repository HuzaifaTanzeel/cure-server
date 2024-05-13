package com.example.demo.Model.Allergy;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PatientAllergyHistoryId {

    @Column(name = "FACILITY_ID")
    private Long allergyId;

    @Column(name = "PATIENT_PID")
    private Long patientPid;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;


}