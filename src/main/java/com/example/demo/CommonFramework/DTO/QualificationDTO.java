package com.example.demo.CommonFramework.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class QualificationDTO {

    @JsonIgnore
    private Long qualificationId;
    private Long partyId;
    private String title;
    private int qualificationTypeCd;
    private String issuerName;
    private Date startDate;
    private Date endDate;
    private boolean inProgressIndicator;

    // Constructors, getters, setters

    public QualificationDTO() {
        // Default constructor
    }

    public QualificationDTO(Long qualificationId, Long partyId, String title, int qualificationTypeCd,
                            String issuerName, Date startDate, Date endDate, boolean inProgressIndicator) {
        this.qualificationId = qualificationId;
        this.partyId = partyId;
        this.title = title;
        this.qualificationTypeCd = qualificationTypeCd;
        this.issuerName = issuerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inProgressIndicator = inProgressIndicator;
    }

    @JsonProperty("qualificationId")
    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQualificationTypeCd() {
        return qualificationTypeCd;
    }

    public void setQualificationTypeCd(int qualificationTypeCd) {
        this.qualificationTypeCd = qualificationTypeCd;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isInProgressIndicator() {
        return inProgressIndicator;
    }

    public void setInProgressIndicator(boolean inProgressIndicator) {
        this.inProgressIndicator = inProgressIndicator;
    }
}
