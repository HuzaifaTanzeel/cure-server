package com.example.demo.CommonFramework.Model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name = "GEN_PERSON"
)
@PrimaryKeyJoinColumn(
        name = "PERSON_PID"
)
public class GenPerson extends GenParty{

//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.IDENTITY
//    )
//    @Column(
//            name = "PERSON_PID"
//    )
    //private int personPid;
    @Column(
            name = "FIRST_NAME"
    )
    private String firstName;
    @Column(
            name = "MIDDLE_NAME"
    )
    private String middleName;
    @Column(
            name = "LAST_NAME"
    )
    private String lastName;
    @Column(
            name = "SUFFIX"
    )
    private String suffix;
    @Column(
            name = "BIRTH_DATE"
    )
    private Date birthDate;
    @Column(
            name = "GENDER_CD"
    )
    private int genderCd;
    @Column(
            name = "MARITAL_STATUS_CD"
    )
    private int maritalStatusCd;
    @Column(
            name = "CREATED_BY"
    )
    private int createdBy;
    @Column(
            name = "CREATED_DT_TM"
    )
    private Date createdDateTime;
    @Column(
            name = "LAST_UPDATED_BY"
    )
    private int lastUpdatedBy;
    @Column(
            name = "LAST_UPDATED_DT_TM"
    )
    private Date lastUpdatedDateTime;
    @Column(
            name = "ACTIVE_IND"
    )
    private String activeInd;
//    @OneToOne
//    @JoinColumn(
//            name = "PERSON_PID"
//    )
    //private GenParty party;

    public GenPerson() {
    }




    public GenPerson(int partyTypeCd, String firstName, String lastName) {
        super(partyTypeCd);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public GenPerson(String firstName, String middleName, String lastName, String suffix, Date birthDate, int genderCd, int maritalStatusCd, int createdBy, Date createdDateTime, int lastUpdatedBy, Date lastUpdatedDateTime, String activeInd) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.birthDate = birthDate;
        this.genderCd = genderCd;
        this.maritalStatusCd = maritalStatusCd;
        this.createdBy = createdBy;
        this.createdDateTime = createdDateTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
        this.activeInd = activeInd;

    }

    @Override
    public String toString() {
        return "GenPerson{" +

                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suffix='" + suffix + '\'' +
                ", birthDate=" + birthDate +
                ", genderCd=" + genderCd +
                ", maritalStatusCd=" + maritalStatusCd +
                ", createdBy=" + createdBy +
                ", createdDateTime=" + createdDateTime +
                ", lastUpdatedBy=" + lastUpdatedBy +
                ", lastUpdatedDateTime=" + lastUpdatedDateTime +
                ", activeInd='" + activeInd + '\'' +
                //", party=" + party +
                '}';
    }

    public String getFirstName() {
        return this.firstName;
    }

//    public void setPersonPid(int personPid) {
//        this.personPid = personPid;
//    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getGenderCd() {
        return this.genderCd;
    }

    public void setGenderCd(int genderCd) {
        this.genderCd = genderCd;
    }

    public int getMaritalStatusCd() {
        return this.maritalStatusCd;
    }

    public void setMaritalStatusCd(int maritalStatusCd) {
        this.maritalStatusCd = maritalStatusCd;
    }



    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }



    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }



    public void setLastUpdatedBy(int lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }



    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public String getActiveInd() {
        return this.activeInd;
    }

    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }

//    public GenParty getParty() {
//        return this.party;
//    }
//
//    public void setParty(GenParty party) {
//        this.party = party;
//    }
}
