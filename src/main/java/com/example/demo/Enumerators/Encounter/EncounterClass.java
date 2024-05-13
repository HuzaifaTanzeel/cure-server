package com.example.demo.Enumerators.Encounter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum EncounterClass {
    AMBULATORY(101, "AMB", "AMBULATORY", "The term ambulatory usually implies that the patient has come to the location and is not assigned to a bed. Sometimes referred to as an outpatient encounter."),
    EMERGENCY(102, "EMER", "EMERGENCY", "A patient encounter that takes place at a dedicated healthcare service delivery location where the patient receives immediate evaluation and treatment, provided until the patient can be discharged or responsibility for the patient's care is transferred"),
    FIELD(103, "FLD", "FIELD", "A patient encounter that takes place both outside a dedicated service delivery location and outside a patient's residence. Example locations might include an accident site and at a supermarket."),
    HOME_HEALTH(104, "HH", "HOME HEALTH", "Healthcare encounter that takes place in the residence of the patient or a designee"),
    INPATIENT(105, "IMP", "IN PATIENT ENCOUNTER", "A patient encounter where a patient is admitted by a hospital or equivalent facility, assigned to a location where patients generally stay at least overnight and provided with room, board, and continuous nursing service."),
    ACUTE(106, "ACUTE", "ACUTE", "An acute inpatient encounter."),
    NON_ACUTE(107, "NONAC", "NON ACUTE", "Any category of inpatient encounter except 'acute'"),
    OBSERVATION(108, "OBSENC", "OBSERVATION", "Encounter require a significant period of treatment and monitoring to determine whether or not their condition warrants an inpatient admission or discharge"),
    PRE_ADMISSION(109, "PRENC", "PRE-ADMISSION", "A patient encounter where patient is scheduled or planned to receive service delivery in the future, and the patient is given a pre-admission account number"),
    SHORT_STAY(110, "SS", "SHORT STAY", "An encounter where the patient is admitted to a health care facility for a predetermined length of time, usually less than 24 hours."),
    VIRTUAL(111, "VR", "VIRTUAL", "A patient encounter where the patient and the practitioner(s) are not in the same physical location. Examples include telephone conference, email exchange, robotic surgery, and televideo conference.");

    private final int code;
    private final String codeSystem;
    private final String display;
    private final String definition;

    EncounterClass(int code, String codeSystem, String display, String definition) {
        this.code = code;
        this.codeSystem = codeSystem;
        this.display = display;
        this.definition = definition;
    }

}

@Converter(autoApply = true)
class EncounterClassConverter implements AttributeConverter<EncounterClass, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EncounterClass attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public EncounterClass convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        for (EncounterClass encounterClass : EncounterClass.values()) {
            if (dbData == encounterClass.getCode()) {
                return encounterClass;
            }
        }
        throw new IllegalArgumentException("Invalid EncounterClass code: " + dbData);
    }
}

