package com.example.demo.CommonFramework.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class PersonDTO {

    private Long partyId;
    private String firstName;
    private String middleName;
    private String lastName;

    @JsonIgnore
    private String suffix;
    private Date birthDate;
    private int genderCd;
    private int maritalStatusCd;

    // Constructors, getters, setters, and other methods

    public PersonDTO() {
        // Default constructor
    }

    public PersonDTO(Long partyId,String firstName, String middleName, String lastName, Date birthDate, int genderCd, int maritalStatusCd) {
        this.partyId=partyId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.genderCd = genderCd;
        this.maritalStatusCd = maritalStatusCd;
    }

    // Add getters and setters for each field

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getGenderCd() {
        return genderCd;
    }

    public void setGenderCd(int genderCd) {
        this.genderCd = genderCd;
    }

    public int getMaritalStatusCd() {
        return maritalStatusCd;
    }

    public void setMaritalStatusCd(int maritalStatusCd) {
        this.maritalStatusCd = maritalStatusCd;
    }
}
