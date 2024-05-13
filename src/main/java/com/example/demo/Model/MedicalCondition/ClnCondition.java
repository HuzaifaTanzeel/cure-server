package com.example.demo.Model.MedicalCondition;


import com.example.demo.Model.Diagnosis.ClnPatientDiagnosis;
import com.example.demo.Model.MedicalHistory.FamilyMemberHistoryCondition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLN_CONDITION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClnCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONDITION_ID")
    private Long conditionId;

    @Column(name = "CONDITION_CODE")
    private String conditionCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DEFINITION")
    private String definition;

    @Column(name = "CODE_SYSTEM")
    private String codeSystem;



    @OneToMany(mappedBy = "clnCondition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClnPatientProblem> patientConditionList=new ArrayList<>();

    // Other methods, if needed

    public void addPatientConditions(ClnPatientProblem cLnPatientProblem) {
        patientConditionList.add(cLnPatientProblem);
        cLnPatientProblem.setClnCondition(this);
    }

    @OneToMany(mappedBy = "clnCondition")
    private List<FamilyMemberHistoryCondition> familyMemberHistoryConditionList=new ArrayList<>();;

    public void addFamilyMemberHistoryConditions(FamilyMemberHistoryCondition familyMemberHistoryCondition) {
        familyMemberHistoryConditionList.add(familyMemberHistoryCondition);
        familyMemberHistoryCondition.setClnCondition(this);
    }

    @OneToMany(mappedBy = "clnCondition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClnPatientDiagnosis> patientDiagnosisList=new ArrayList<>();

    public void addPatientDiagnosis(ClnPatientDiagnosis clnPatientDiagnosis) {
        patientDiagnosisList.add(clnPatientDiagnosis);
        clnPatientDiagnosis.setClnCondition(this);
    }
}
