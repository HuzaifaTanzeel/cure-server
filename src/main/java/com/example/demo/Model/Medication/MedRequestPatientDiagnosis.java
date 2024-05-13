package com.example.demo.Model.Medication;

import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MED_MR_PAT_DIAGNOSIS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedRequestPatientDiagnosis {

    @EmbeddedId
    MedRequestPatientDiagnosisId medRequestPatientDiagnosisId;


    @ManyToOne
    @MapsId("medReqId")
    @JoinColumn(name = "MED_REQ_ID")
    private MedicationRequest medicationRequest;

    //@Id
    @ManyToOne
    @MapsId("diagnosisId")
    @JoinColumn(name = "DIAGNOSIS_ID")
    private ClnPatientDiagnosis clnPatientDiagnosis;

    public MedRequestPatientDiagnosis(MedicationRequest medicationRequest, ClnPatientDiagnosis clnPatientDiagnosis) {
        this.medRequestPatientDiagnosisId=new MedRequestPatientDiagnosisId(medicationRequest.getMedReqId(),clnPatientDiagnosis.getDiagnosisId());
        this.medicationRequest = medicationRequest;
        this.clnPatientDiagnosis = clnPatientDiagnosis;
    }
}
