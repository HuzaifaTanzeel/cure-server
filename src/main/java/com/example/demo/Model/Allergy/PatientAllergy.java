package com.example.demo.Model.Allergy;


import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Enumerators.AllergyStatus;
import com.example.demo.CommonFramework.Enumerators.AllergyType;
import com.example.demo.CommonFramework.Enumerators.ClinicalStatus;
import com.example.demo.Model.AppointmentManagement.Encounter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ALLERGY_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientAllergy {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PATIENT_ALLERGY_ID")
    private Long patientAllergyId;

    @Column(name = "ALLERGEN_ID")
    private Long allergenId;

    @Column(name = "PATIENT_PID")
    private Long patientId;

    @Column(name = "PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "ALLERGY_TYPE")
    //@Enumerated(EnumType.ORDINAL)
    private AllergyType allergyTypeCd;

    @Column(name = "ALLERGY_STATUS")
    //@Enumerated(EnumType.ORDINAL)
    private AllergyStatus allergyStatusCd;

    @Column(name = "CLINICAL_STATUS")
    //@Enumerated(EnumType.ORDINAL)
    private ClinicalStatus clinicalStatusCd;

    @Column(name = "ENCOUNTER_ID")
    private Long encounterId;

    @Column(name = "ONSET_DT_TM")
    private LocalDateTime onsetDateTime;

    @Column(name = "RESOLVED_DT_TM")
    private LocalDateTime resolvedDateTime;

    @Column(name = "LAST_OCCURRENCE_DT_TM")
    private LocalDateTime lastOccurrenceDateTime;

    @Column(name = "COMMENTS", length = 255)
    private String comments;



    

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PATIENT_PID", referencedColumnName = "PARTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "PARTY_ROLE_TYPE_ID", referencedColumnName = "PARTY_ROLE_TYPE_ID", insertable = false, updatable = false)
    })
    private AdmPatient admPatient;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ALLERGEN_ID", referencedColumnName = "ALLERGEN_ID", nullable = false,insertable = false,updatable = false)
    private Allergen allergen;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ENCOUNTER_ID", referencedColumnName = "ENCOUNTER_ID", insertable = false, updatable = false)
    private Encounter encounter;

    @OneToMany(mappedBy = "patientAllergy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientAllergyReaction> patientAllergyReactionList=new ArrayList<>();

    // Other methods, if needed

    public void addPatientAllergyReactions(PatientAllergyReaction patientAllergyReaction) {
        patientAllergyReactionList.add(patientAllergyReaction);
        patientAllergyReaction.setPatientAllergy(this);
    }


}




