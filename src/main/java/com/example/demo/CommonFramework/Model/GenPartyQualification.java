package com.example.demo.CommonFramework.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "GEN_PARTY_QUALIFICATION")
public class GenPartyQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUALIFICATION_ID")
    private Long qualificationId;

    @ManyToOne
    @JoinColumn(name = "PARTY_ID",insertable = false,updatable = false)
    private GenParty genParty;

    @Column(name = "PARTY_ID")
    private Long partyId;


    @Column(name = "TITLE", length = 40, nullable = false)
    private String title;

    @Column(name = "QUALIFICATION_TYPE_CD", nullable = false)
    private int qualificationTypeCd;

    @Column(name = "ISSUER_NAME", length = 40)
    private String issuerName;

    @Column(name = "START_DT", nullable = false)
    private Date startDate;

    @Column(name = "END_DT")
    private Date endDate;

    @Column(name = "IN_PROGRESS_IND")
    private boolean inProgressIndicator;

    @Lob
    @Column(name = "DOCUMENT_ATCH")
    private byte[] documentAttachment;

    public GenPartyQualification(Long partyId, String title, int qualificationTypeCd, String issuerName, Date startDate, Date endDate, boolean inProgressIndicator) {
        this.partyId = partyId;
        this.title = title;
        this.qualificationTypeCd = qualificationTypeCd;
        this.issuerName = issuerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.inProgressIndicator = inProgressIndicator;
    }

    public GenPartyQualification() {
    }

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public GenParty getGenParty() {
        return genParty;
    }

    public void setGenParty(GenParty genParty) {
        this.genParty = genParty;
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

    // Constructors, getters, and setters

    // ...
}

