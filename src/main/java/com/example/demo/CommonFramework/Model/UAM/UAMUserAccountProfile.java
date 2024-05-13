package com.example.demo.CommonFramework.Model.UAM;

import com.example.demo.CommonFramework.Model.GenParty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="UAM_USER_ACCOUNT_PROFILE")
public class UAMUserAccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ACCOUNT_ID")
    private Long userAccountId;


    @Column(name = "PARTY_ID")
    private Long partyId;


    @Temporal(TemporalType.DATE)
    @Column(name = "SUBSCRIPTION_START_DT")
    private Date subscriptionStartDate;


    @Temporal(TemporalType.DATE)
    @Column(name = "SUBSCRIPTION_END_DT")
    private Date subscriptionEndDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "USER_ACCOUNT_ID")
    private UAMUserAccount userAccount;



    @ManyToOne
    @JoinColumn(name = "PARTY_ID",insertable = false, updatable = false)
    private GenParty genParty;

    public UAMUserAccountProfile() {
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }



    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public UAMUserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UAMUserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public GenParty getGenParty() {
        return genParty;
    }

    public void setGenParty(GenParty genParty) {
        this.genParty = genParty;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }
}
