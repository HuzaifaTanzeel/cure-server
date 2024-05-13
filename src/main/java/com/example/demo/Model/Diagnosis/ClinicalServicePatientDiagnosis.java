package com.example.demo.Model.Diagnosis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CLN_SR_PAT_DIAGNOSIS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalServicePatientDiagnosis {

    @EmbeddedId
    ClinicalServicePatientDiagnosisId clinicalServicePatientDiagnosisId;


    @ManyToOne
    @MapsId("servReqId")
    @JoinColumn(name = "SERV_REQ_ID")
    private ClnServiceRequest clnServiceRequest;

    //@Id
    @ManyToOne
    @MapsId("diagnosisId")
    @JoinColumn(name = "DIAGNOSIS_ID")
    private ClnPatientDiagnosis clnPatientDiagnosis;

    public ClinicalServicePatientDiagnosis(ClnServiceRequest clnServiceRequest, ClnPatientDiagnosis clnPatientDiagnosis) {
        this.clinicalServicePatientDiagnosisId=new ClinicalServicePatientDiagnosisId(clnServiceRequest.getServReqId(),clnPatientDiagnosis.getDiagnosisId());
        this.clnServiceRequest = clnServiceRequest;
        this.clnPatientDiagnosis = clnPatientDiagnosis;
    }
}
