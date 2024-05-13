package com.example.demo.Model.Observation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DGN_LOINC_PANEL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DgnLoincPanel {


    @Column(name = "PARENT_LOINC_ID", length = 10)
    private String parentLoincId;

    @Column(name = "PARENT_LOINC_NUM", length = 10)
    private String parentLoincNumber;

    @Column(name = "PARENT_LOINC_NAME", length = 200)
    private String parentLoincName;

    @Id
    @Column(name = "LOINC_ID", length = 10)
    private String loincId;

    @Column(name = "SEQUENCE")
    private Short sequence;

    @Column(name = "LOINC_NUM", length = 10)
    private String loincNumber;

    @Column(name = "LOINC_NAME", length = 200)
    private String loincName;

    @Column(name = "DISPLAY_NAME_FOR_FORM", length = 40)
    private String displayNameForForm;

    @Column(name = "OBSERVATION_REQUIRED_IN_PANEL", length = 10)
    private String observationRequiredInPanel;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_LOINC_NUM",referencedColumnName = "LOINC_NUM", insertable = false, updatable = false)
    private DgnLoinc parentLoinc;

    // Constructors, getters, and setters
}