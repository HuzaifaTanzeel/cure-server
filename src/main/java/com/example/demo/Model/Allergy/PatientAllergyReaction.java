package com.example.demo.Model.Allergy;

import com.example.demo.CommonFramework.Enumerators.AllergySeverity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CLN_PAT_ALLERGY_REACTION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientAllergyReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALLERGY_REACTION_ID")
    private Long allergyReactionId;

    @Column(name="PATIENT_ALLERGY_ID")
    private Long patientAllergyId;

    @Column(name = "REACTION")
    private String reaction;

    @Column(name = "SEVERITY_CD")
    private AllergySeverity severityCd;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ALLERGY_ID", referencedColumnName = "PATIENT_ALLERGY_ID",insertable = false, updatable = false, nullable = false)
    private PatientAllergy patientAllergy;


}
