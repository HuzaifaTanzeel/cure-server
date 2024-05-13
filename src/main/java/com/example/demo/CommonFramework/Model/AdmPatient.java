package com.example.demo.CommonFramework.Model;



import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleType;
import com.example.demo.Model.Allergy.PatientAllergy;
import com.example.demo.Model.AppointmentManagement.Appointment;
import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
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
@Table(
        name = "PATIENT"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmPatient extends GenPartyRole {


    public AdmPatient(GenPartyRoleId genPartyRoleId, GenParty partyId, GenPartyRoleType partyRoleTypeId) {
        super(genPartyRoleId, partyId, partyRoleTypeId);
    }



    public AdmPatient(GenPerson genPerson, GenPartyRoleType partyRoleTypeId) {
        super(genPerson, partyRoleTypeId);
    }

    @OneToMany(mappedBy = "admPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClnPatientProblem> patientConditionList = new ArrayList<>();



    public void addPatientConditions(ClnPatientProblem cLnPatientProblem) {
        patientConditionList.add(cLnPatientProblem);
        cLnPatientProblem.setAdmPatient(this);
    }

    @OneToMany(mappedBy = "admPatient", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PatientAllergy> patientAllergies = new ArrayList<>();

    public void addPatientAllergies(PatientAllergy patientAllergy) {
        patientAllergies.add(patientAllergy);
        patientAllergy.setAdmPatient(this);

    }


    @OneToMany(mappedBy = "admPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamilyMemberHistory> familyMemberHistoryList = new ArrayList<>();

    public void addFamilyMemberHistories(FamilyMemberHistory familyMemberHistory) {
        familyMemberHistoryList.add(familyMemberHistory);
        familyMemberHistory.setAdmPatient(this);

    }

    @OneToMany(mappedBy = "admPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientSocialHistory> patientSocialHistoryList=new ArrayList<>();

    public void addPatientSocialHistory(PatientSocialHistory patientSocialHistory) {
        patientSocialHistoryList.add(patientSocialHistory);
        patientSocialHistory.setAdmPatient(this);
    }


    @OneToMany(mappedBy = "admPatient", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PatientSurgicalHistory> patientSurgicalHistoryList=new ArrayList<>();

    public void addPatientSurgicalHistory(PatientSurgicalHistory patientSurgicalHistory) {
        patientSurgicalHistoryList.add(patientSurgicalHistory);
        patientSurgicalHistory.setAdmPatient(this);
    }


    @OneToMany(mappedBy = "admPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClnPatientDiagnosis> patientDiagnosisList = new ArrayList<>();


    public void addPatientDiagnosis(ClnPatientDiagnosis clnPatientDiagnosis) {
        patientDiagnosisList.add(clnPatientDiagnosis);
        clnPatientDiagnosis.setAdmPatient(this);
    }

    @OneToMany(mappedBy = "admPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicationRequest> medicationRequestList = new ArrayList<>();


    public void addPatientMedicationRequest(MedicationRequest medicationRequest) {
        medicationRequestList.add(medicationRequest);
        medicationRequest.setAdmPatient(this);
    }

    @OneToMany(mappedBy = "admPatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentList = new ArrayList<>();


    public void addAppointment(Appointment appointment) {
        appointmentList.add(appointment);
        appointment.setAdmPatient(this);
    }



}
