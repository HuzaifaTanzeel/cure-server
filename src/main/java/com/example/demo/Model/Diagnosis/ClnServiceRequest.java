package com.example.demo.Model.Diagnosis;

import com.example.demo.Enumerators.Diagnosis.ReqPriority;
import com.example.demo.Enumerators.Diagnosis.RequestIntent;
import com.example.demo.Enumerators.Diagnosis.RequestStatus;
import com.example.demo.Enumerators.Diagnosis.ServReqCategory;
import com.example.demo.Model.AppointmentManagement.Encounter;
import com.example.demo.Model.MedicalHistory.ClinicalProcedure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLN_SERVICE_REQUEST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClnServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERV_REQ_ID")
    private Long servReqId;

    @Column(name = "ENCOUNTER_ID")
    private Long encounterId;

    @Column(name = "PROCEDURE_ID")
    private Long procedureId;

    @Column(name = "CATEGORY_CD")
    private ServReqCategory categoryCode;

    @Column(name = "PRIORITY_CD")
    private ReqPriority priorityCd;

    @Column(name = "INTENT_CD")
    private RequestIntent intentCd;

    @Column(name = "STATUS_CD")
    private RequestStatus statusCd;

    @Column(name = "GROUP_CODE")
    private String groupCode;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "DUE_DT_TM")
    private LocalDate dueDateTime;

    @Column(name = "AUTHORED_ON_DT_TM")
    private LocalDateTime authoredOnDateTime;

    @Column(name = "REQUESTED_LOC_NAME")
    private String requestedLocationName;

    @Column(name = "SUPPORTING_INFO", length = 255)
    private String supportingInfo;

    @Column(name = "PATIENT_INSTRUCTION", length = 255)
    private String patientInstruction;


    @ManyToOne
    @JoinColumn(name = "ENCOUNTER_ID", referencedColumnName = "ENCOUNTER_ID", insertable = false, updatable = false)
    private Encounter encounter;

    @ManyToOne
    @JoinColumn(name = "PROCEDURE_ID", referencedColumnName = "PROCEDURE_ID",insertable = false, updatable = false)
    private ClinicalProcedure clinicalProcedure;

    @OneToMany(mappedBy = "clnServiceRequest", cascade = CascadeType.ALL)
    private List<ClinicalServicePatientDiagnosis> clinicalServicePatientDiagnosisList=new ArrayList<>();;

    public void addPatientServiceRequestDiagnosis(ClinicalServicePatientDiagnosis clinicalServicePatientDiagnosis) {
        clinicalServicePatientDiagnosisList.add(clinicalServicePatientDiagnosis);
        clinicalServicePatientDiagnosis.setClnServiceRequest(this);
    }
}
