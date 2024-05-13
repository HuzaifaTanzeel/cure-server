package com.example.demo.Model.Observation;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DGN_LOINC")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DgnLoinc {

    @Id
    @Column(name = "LOINC_NUM", length = 10)
    private String loincNum;

    @Column(name = "COMPONENT", length = 255)
    private String component;

    @Column(name = "CLASS", length = 255)
    private String observationClass;

    @Column(name = "SHORTNAME", length = 255)
    private String shortName;

    @Column(name = "LONG_COMMON_NAME", length = 255)
    private String longName;

    @Column(name = "CLASSTYPE")
    private Integer classType;

    @Column(name = "PANELTYPE", length = 50)
    private String panelType;


    @OneToMany(mappedBy = "parentLoinc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DgnLoincPanel> loincPanels = new ArrayList<>();


    public void addLoincPanel(DgnLoincPanel dgnLoincPanel) {
        loincPanels.add(dgnLoincPanel);
        dgnLoincPanel.setParentLoinc(this);
    }



}
