package com.example.demo.Model.Medication;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.Enumerators.Diagnosis.ReqPriority;
import com.example.demo.Enumerators.Medication.*;
import com.example.demo.Model.AppointmentManagement.Encounter;
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
@Table(name = "MED_MEDICATION_REQUEST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MED_REQ_ID")
    private Long medReqId;

    @Column(name = "PATIENT_PID")
    private Long patientId;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "DRUG_ID")
    private Long drugId;

    @Column(name = "MED_NAME")
    private String medicationName;

    @Column(name = "ENCOUNTER_ID")
    private Long encounterId;

    @Column(name = "MED_REQ_CAT_CD")
    private MedReqCategory requestCd;

    @Column(name = "ROUTE_CD")
    private Route routeCd;

    @Column(name = "DOSE_FORM_CD")
    private DrugForm doseFormCd;

    @Column(name = "DOSAGE_QTY")
    private Double dosageQuantity;

    @Column(name = "FREQUENCY_CD")
    private MedFrequency frequencyCd;

    @Column(name = "DAYS_SUPPLY")
    private Double daysSupply;

    @Column(name = "TOTAL_QTY")
    private Double totalQuantity;

    @Column(name = "AS_NEEDED_FLG")
    private Character asNeededFlag;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "PRIORITY_CD")
    private ReqPriority priorityCd;

    @Column(name = "REQ_DT_TM")
    private LocalDateTime requestDtTm;

    @Column(name = "STATUS_CD")
    private MedReqStatus statusCd;

    @Column(name = "INTENT_CD")
    private MedReqIntent intentCd;

    @Column(name = "GROUP_CODE")
    private String groupCode;

    @Column(name = "PAT_INSTRUCTION")
    private String patientInstruction;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "REQUESTED_BY_PID")
    private Long requestedByPid;

    @Column(name = "RECORDED_BY_PID")
    private Long recordedByPid;


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
    @JoinColumn(name = "DRUG_ID", referencedColumnName = "DRUG_ID",insertable = false, updatable = false)
    private Drug drug;

    @OneToMany(mappedBy = "medicationRequest", cascade = CascadeType.ALL)
    private List<MedRequestPatientDiagnosis> medRequestPatientDiagnosisList=new ArrayList<>();;

    public void addPatientMedRequestDiagnosis(MedRequestPatientDiagnosis medRequestPatientDiagnosis) {
        medRequestPatientDiagnosisList.add(medRequestPatientDiagnosis);
        medRequestPatientDiagnosis.setMedicationRequest(this);
    }
}
