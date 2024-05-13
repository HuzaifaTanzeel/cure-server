package com.example.demo.Model.MedicalHistory;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.Enumerators.MedicalHistory.SmokingStatus;
import com.example.demo.Model.AppointmentManagement.Encounter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "patient_social_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientSocialHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_SOCIAL_HX_ID")
    private Long patientSocialHxId;

    @Column(name = "PATIENT_ID")
    private Long patientId;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "ENCOUNTER_ID")
    private Long encounterId;

    @Column(name = "RECORDED_DT")
    private Date recordedDate;

    @Column(name = "SMOKING_STATUS_CD")
    private SmokingStatus smokingStatusCd;

    @Column(name = "TOBACCO_PAK_PER_DY")
    private int tobaccoPakPerDay;

    @Column(name = "TOBACCO_USER_YEARS")
    private int tobaccoUserYears;

    @Column(name = "SMOKING_QUIT_DT")
    private Date smokingQuitDate;

    @Column(name = "CIGARETTES_IND")
    private char cigarettesInd;

    @Column(name = "PIPES_IND")
    private char pipesInd;

    @Column(name = "CIGARS_IND")
    private char cigarsInd;

    @Column(name = "SNUFF_IND")
    private char snuffInd;

    @Column(name = "CHEW_IND")
    private char chewInd;

    @Column(name = "COMMENT", length = 255)
    private String comment;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PARTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID", insertable = false, updatable = false)
    })
    private AdmPatient admPatient;

    @ManyToOne
    @JoinColumn(name = "ENCOUNTER_ID", referencedColumnName = "ENCOUNTER_ID", insertable = false, updatable = false)
    private Encounter encounter;

    // Constructors, getters, and setters
}
