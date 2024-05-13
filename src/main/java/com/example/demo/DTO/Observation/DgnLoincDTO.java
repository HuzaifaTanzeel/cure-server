package com.example.demo.DTO.Observation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DgnLoincDTO {

    private String code;
    private String component;
    private String observationClass;
    private String shortName;
    private String longName;
    private Integer classType;
    private String panelType;

}