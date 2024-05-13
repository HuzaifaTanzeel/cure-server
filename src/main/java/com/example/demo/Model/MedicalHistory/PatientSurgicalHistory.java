package com.example.demo.Model.MedicalHistory;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.Model.AppointmentManagement.Encounter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "CLN_PATIENT_SURGICAL_HX")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientSurgicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_SURGICAL_HX_ID")
    private Long patientSurgicalHxId;

    @Column(name = "PATIENT_PID")
    private Long patientId;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "ENCOUNTER_ID")
    private Long encounterId;

    @Column(name = "PROCEDURE_ID")
    private Long procedureId;

    @Column(name = "SURGERY_NAME", length = 40)
    private String surgeryName;

    @Column(name = "RECORDED_DT")
    private Date recordedDate;

    @Column(name = "SURGERY_DT")
    private Date surgeryDate;

    @Column(name = "COMMENT", length = 255)
    private String comment;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PATIENT_PID", referencedColumnName = "PARTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID", insertable = false, updatable = false)
    })
    private AdmPatient admPatient;

    @ManyToOne
    @JoinColumn(name = "ENCOUNTER_ID", referencedColumnName = "ENCOUNTER_ID", insertable = false, updatable = false)
    private Encounter encounter;

    @ManyToOne
    @JoinColumn(name = "PROCEDURE_ID", referencedColumnName = "PROCEDURE_ID",insertable = false, updatable = false)
    private ClinicalProcedure clinicalProcedure;

    // Constructors, getters, and setters
}