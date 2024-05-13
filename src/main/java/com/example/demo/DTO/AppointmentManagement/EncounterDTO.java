package com.example.demo.DTO.AppointmentManagement;

import com.example.demo.Enumerators.Encounter.EncounterStatus;
import com.example.demo.Enumerators.Encounter.EncounterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncounterDTO {

    private Long EncounterId;
    private Long appointmentId;
    private EncounterType encounterTypeCd;
    private EncounterStatus encounterStatusCd;
    private String encounterSummary;
    // Getters and setters
}

