package com.example.demo.Model.Medication;


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
public class MedRequestPatientDiagnosisId implements Serializable {

    @Column(name = "MED_REQ_ID")
    private Long medReqId;

    @Column(name = "DIAGNOSIS_ID")
    private Long diagnosisId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedRequestPatientDiagnosisId that = (MedRequestPatientDiagnosisId) o;
        return Objects.equals(medReqId, that.medReqId) && Objects.equals(diagnosisId, that.diagnosisId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medReqId, diagnosisId);
    }
}

