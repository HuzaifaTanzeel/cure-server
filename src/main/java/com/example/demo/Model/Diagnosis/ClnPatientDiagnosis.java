package com.example.demo.Model.Diagnosis;


import com.example.demo.CommonFramework.Enumerators.ClinicalStatus;
import com.example.demo.CommonFramework.Enumerators.CondSeverity;
import com.example.demo.CommonFramework.Enumerators.CondVerfStatus;
import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.MedicalCondition.ClnCondition;
import com.example.demo.Model.Medication.MedRequestPatientDiagnosis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLN_PAT_DIAGNOSIS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClnPatientDiagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIAGNOSIS_ID")
    private Long diagnosisId;

    @Column(name = "PATIENT_ID")
    private Long patientId;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "CONDITION_ID")
    private Long conditionId;

    @Column(name = "ENCOUNTER_ID")
    private Long encounterId;

    @Column(name = "CLINICAL_STATUS_CD")
    private ClinicalStatus clinicalStatusCd;

    @Column(name = "VERF_STATUS_CD")
    private CondVerfStatus verificationStatusCd;

    @Column(name = "SEVERITY_CD")
    private CondSeverity severityCd;

    @Column(name = "RECORDED_DATE_DT")
    private LocalDate recordedDateDt;

    @Column(name = "ONSET_DT_TM")
    private LocalDateTime onsetDtTm;

    @Column(name = "CHRONIC_IND")
    private String chronicInd;

    @Column(name = "COMMENT", length = 255)
    private String comment;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PARTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID", insertable = false, updatable = false)
    })
    private AdmPatient admPatient;

    @ManyToOne
    @JoinColumn(name = "CONDITION_ID", referencedColumnName = "CONDITION_ID", nullable = false,insertable = false,updatable = false)
    private ClnCondition clnCondition;

    @ManyToOne
    @JoinColumn(name = "ENCOUNTER_ID", referencedColumnName = "ENCOUNTER_ID", insertable = false, updatable = false)
    private Encounter encounter;


    @OneToMany(mappedBy = "clnPatientDiagnosis", cascade = CascadeType.ALL)
    private List<ClinicalServicePatientDiagnosis> clinicalServicePatientDiagnosisList=new ArrayList<>();;

    public void addPatientServiceRequestDiagnosis(ClinicalServicePatientDiagnosis clinicalServicePatientDiagnosis) {
        clinicalServicePatientDiagnosisList.add(clinicalServicePatientDiagnosis);
        clinicalServicePatientDiagnosis.setClnPatientDiagnosis(this);
    }


    @OneToMany(mappedBy = "clnPatientDiagnosis", cascade = CascadeType.ALL)
    private List<MedRequestPatientDiagnosis> medRequestPatientDiagnosisList=new ArrayList<>();;

    public void addPatientMedRequestDiagnosis(MedRequestPatientDiagnosis medRequestPatientDiagnosis) {
        medRequestPatientDiagnosisList.add(medRequestPatientDiagnosis);
        medRequestPatientDiagnosis.setClnPatientDiagnosis(this);
    }



}
