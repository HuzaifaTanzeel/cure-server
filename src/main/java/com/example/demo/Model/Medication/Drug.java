package com.example.demo.Model.Medication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "DRUG_MSTR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DRUG_ID")
    private Long drugId;

    @Column(name = "RXCUI")
    private String rxcui;

    @Column(name = "LAT")
    private String lat;

    @Column(name = "TS")
    private String ts;

    @Column(name = "LUI")
    private String lui;

    @Column(name = "STT")
    private String stt;

    @Column(name = "SUI")
    private String sui;

    @Column(name = "ISPREF")
    private String isPref;

    @Column(name = "RXAUI")
    private String rxaui;

    @Column(name = "SAUI")
    private String saui;

    @Column(name = "SCUI")
    private String scui;

    @Column(name = "SDUI")
    private String sdui;

    @Column(name = "SAB")
    private String sab;

    @Column(name = "TTY")
    private String tty;

    @Column(name = "CODE")
    private String code;

    @Column(name = "STR", length = 3000)
    private String str;

    @Column(name = "SRL")
    private String srl;

    @Column(name = "SUPPRESS")
    private String suppress;

    @Column(name = "CVF")
    private String cvf;

    // Constructors, getters, and setters
}
