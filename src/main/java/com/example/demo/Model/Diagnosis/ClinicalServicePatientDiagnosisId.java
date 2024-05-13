package com.example.demo.Model.Diagnosis;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClinicalServicePatientDiagnosisId implements Serializable {

    @Column(name = "SERV_REQ_ID")
    private Long servReqId;

    @Column(name = "DIAGNOSIS_ID")
    private Long diagnosisId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClinicalServicePatientDiagnosisId that = (ClinicalServicePatientDiagnosisId) o;
        return Objects.equals(servReqId, that.servReqId) && Objects.equals(diagnosisId, that.diagnosisId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servReqId, diagnosisId);
    }
}
