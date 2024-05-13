package com.example.demo.DTO.Medication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrugDTO {

    private Long drugId;
    private String rxcui;
    private String lat;
    private String ts;
    private String lui;
    private String stt;
    private String sui;
    private String isPref;
    private String rxaui;
    private String saui;
    private String scui;
    private String sdui;
    private String sab;
    private String tty;
    private String code;
    private String str;
    private String srl;
    private String suppress;
    private String cvf;
}