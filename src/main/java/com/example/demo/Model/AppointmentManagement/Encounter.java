package com.example.demo.Model.AppointmentManagement;

import com.example.demo.Enumerators.Encounter.EncounterStatus;
import com.example.demo.Enumerators.Encounter.EncounterType;
import com.example.demo.Model.Allergy.PatientAllergy;
import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
import com.example.demo.Model.Diagnosis.ClnServiceRequest;
import com.example.demo.Model.MedicalCondition.ClnPatientProblem;
import com.example.demo.Model.MedicalHistory.FamilyMemberHistory;
import com.example.demo.Model.MedicalHistory.PatientSocialHistory;
import com.example.demo.Model.MedicalHistory.PatientSurgicalHistory;
import com.example.demo.Model.Medication.MedicationRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ENCOUNTER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENCOUNTER_ID")
    private Long EncounterId;

    @Column(name = "APPOINTMENT_ID")
    private Long AppointmentId;

    @Column(name = "ENCOUNTER_TYPE_CD")
    private EncounterType EncounterTypeCd;

    @Column(name = "ENCOUNTER_STATUS_CD")
    private EncounterStatus EncounterStatusCd;

    @Column(name = "ENCOUNTER_SUMMARY")
    private String EncounterSummary;

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientAllergy> patientAllergies=new ArrayList<>();

    public void addPatientAllergies(PatientAllergy patientAllergy) {
        patientAllergies.add(patientAllergy);
        patientAllergy.setEncounter(this);
        //allergyHistory//allergyHistory
    }

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClnPatientProblem> patientConditionList=new ArrayList<>();

    public void addPatientConditions(ClnPatientProblem cLnPatientProblem) {
        patientConditionList.add(cLnPatientProblem);
        cLnPatientProblem.setEncounter(this);
    }

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamilyMemberHistory> familyMemberHistoryList=new ArrayList<>();

    public void addFamilyMemberHistories(FamilyMemberHistory familyMemberHistory) {
        familyMemberHistoryList.add(familyMemberHistory);
        familyMemberHistory.setEncounter(this);
    }

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientSocialHistory> patientSocialHistoryList=new ArrayList<>();

    public void addPatientSocialHistory(PatientSocialHistory patientSocialHistory) {
        patientSocialHistoryList.add(patientSocialHistory);
        patientSocialHistory.setEncounter(this);
    }

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientSurgicalHistory> patientSurgicalHistoryList=new ArrayList<>();

    public void addPatientSurgicalHistory(PatientSurgicalHistory patientSurgicalHistory) {
        patientSurgicalHistoryList.add(patientSurgicalHistory);
        patientSurgicalHistory.setEncounter(this);
    }

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClnServiceRequest> clnServiceRequestList=new ArrayList<>();

    public void addServiceRequest(ClnServiceRequest clnServiceRequest) {
        clnServiceRequestList.add(clnServiceRequest);
        clnServiceRequest.setEncounter(this);
    }

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClnPatientDiagnosis> patientDiagnosisList=new ArrayList<>();

    public void addPatientDiagnosis(ClnPatientDiagnosis clnPatientDiagnosis) {
        patientDiagnosisList.add(clnPatientDiagnosis);
        clnPatientDiagnosis.setEncounter(this);
    }


    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicationRequest> medicationRequestList=new ArrayList<>();

    public void addMedicationRequest(MedicationRequest medicationRequest) {
        medicationRequestList.add(medicationRequest);
        medicationRequest.setEncounter(this);
    }



    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_ID", referencedColumnName = "APPOINTMENT_ID",insertable = false, updatable = false)
    private Appointment appointment;
    // Constructors, getters, and setters
}

