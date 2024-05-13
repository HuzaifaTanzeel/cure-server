package com.example.demo.Model.MedicalHistory;

import com.example.demo.CommonFramework.Enumerators.ProcedureCategory;
import com.example.demo.Model.Diagnosis.ClnServiceRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLN_PROCEDURES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROCEDURE_ID")
    private Long procedureId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY_CD")
    private ProcedureCategory category;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "clinicalProcedure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientSurgicalHistory> patientSurgicalHistoryList=new ArrayList<>();

    public void addPatientSocialHistory(PatientSurgicalHistory patientSurgicalHistory) {
        patientSurgicalHistoryList.add(patientSurgicalHistory);
        patientSurgicalHistory.setClinicalProcedure(this);
    }

    @OneToMany(mappedBy = "clinicalProcedure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClnServiceRequest> clnServiceRequestList=new ArrayList<>();

    public void addServiceRequest(ClnServiceRequest clnServiceRequest) {
        clnServiceRequestList.add(clnServiceRequest);
        clnServiceRequest.setClinicalProcedure(this);
    }

    // Constructors, getters, and setters
}
