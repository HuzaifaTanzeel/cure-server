package com.example.demo.Model.MedicalHistory;

import com.example.demo.CommonFramework.Enumerators.GenderType;
import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.Enumerators.MedicalHistory.FamilyHistoryStatus;
import com.example.demo.Enumerators.MedicalHistory.FamilyMemberRelationship;
import com.example.demo.Model.AppointmentManagement.Encounter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "family_member_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAMILY_MEMBER_HX_ID")
    private Long familyMemberHxId;

    @Column(name = "PATIENT_PID")
    private Long patientId;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "ENCOUNTER_ID")
    private Long encounterId;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "GENDER_CD")
    private GenderType genderCd;

    @Column(name = "RELATIONSHIP_CD")
    private FamilyMemberRelationship relationshipCd;

    @Column(name = "DOB_DT")
    private LocalDate dobDt;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "DECEASED_IND")
    private Boolean deceasedInd;

    @Column(name = "DECEASED_DATE_DT")
    private LocalDate deceasedDateDt;

    @Column(name = "RECORDED_DATE_DT")
    private LocalDate recordedDateDt;

    @Column(name = "STATUS_CD")
    private FamilyHistoryStatus statusCd;

    @Column(name = "COMMENT")
    private String comment;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PATIENT_PID", referencedColumnName = "PARTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID", insertable = false, updatable = false)
    })
    private AdmPatient admPatient;

    @ManyToOne
    @JoinColumn(name = "ENCOUNTER_ID", referencedColumnName = "ENCOUNTER_ID", insertable = false, updatable = false)
    private Encounter encounter;

    @OneToMany(mappedBy = "familyMemberHistory", cascade = CascadeType.ALL)
    private List<FamilyMemberHistoryCondition> familyMemberHistoryConditionList=new ArrayList<>();;

    public void addFamilyMemberHistoryConditions(FamilyMemberHistoryCondition familyMemberHistoryCondition) {
        familyMemberHistoryConditionList.add(familyMemberHistoryCondition);
        familyMemberHistoryCondition.setFamilyMemberHistory(this);
    }


    // Constructors, getters, and setters
}
