package com.example.demo.CommonFramework.Model;

import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(
        name="REGISTRATION_REQUEST_PRACTITIONER"
)
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_ID", nullable = false)
    private int requestId;

    @Column(name = "REQUEST_TYPE_CD", nullable = false)
    private int requestTypeCd;

    @Column(name="PARTY_ROLE_TYPE_ID")
    private Long partyRoleTypeId;

    @Column(name = "FIRST_NAME", length = 40)
    private String firstName;

    @Column(name = "LAST_NAME", length = 40)
    private String lastName;

    @Column(name = "EMAIL", length = 40)
    private String email;

    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Column(name = "LICENSE_NO", length = 20)
    private String licenseNo;

    @Column(name = "USER_ACCOUNT_ID")
    private int userAccountId;

    @Column(name = "USER_NAME", length = 40)
    private String userName;

    @Column(name = "PASSWORD", length = 64)
    private String password;

    @Column(name = "REGISTRATION_METHOD_CD")
    private int registrationMethodCd;

    @Column(name = "PRACTICE_PID")
    private int practicePid;

    @Column(name = "ACCESS_ROLE_ID")
    private int accessRoleId;

    @Column(name = "REQUEST_DT_TM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDateTime;

    @Column(name = "OTP", length = 6)
    private String otp;

    @Column(name = "OTP_EXPIRY_DT_TM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date otpExpiryDateTime;

    @Column(name = "REQUEST_STATUS_CD")
    private int requestStatusCd;

    @Column(name = "PARENT_REQ_ID")
    private int parentRequestId;

    @ManyToOne
    @JoinColumn(name = "USER_ACCOUNT_ID",insertable = false, updatable = false)
    private UAMUserAccount userAccount;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String firstName, String lastName, String email,String password, String mobile, String licenseNo,Long partyRoleTypeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.licenseNo = licenseNo;
        this.password = password;
        this.partyRoleTypeId=partyRoleTypeId;
    }

    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(Long partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestTypeCd() {
        return requestTypeCd;
    }

    public void setRequestTypeCd(int requestTypeCd) {
        this.requestTypeCd = requestTypeCd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRegistrationMethodCd() {
        return registrationMethodCd;
    }

    public void setRegistrationMethodCd(int registrationMethodCd) {
        this.registrationMethodCd = registrationMethodCd;
    }

    public int getPracticePid() {
        return practicePid;
    }

    public void setPracticePid(int practicePid) {
        this.practicePid = practicePid;
    }

    public int getAccessRoleId() {
        return accessRoleId;
    }

    public void setAccessRoleId(int accessRoleId) {
        this.accessRoleId = accessRoleId;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getOtpExpiryDateTime() {
        return otpExpiryDateTime;
    }

    public void setOtpExpiryDateTime(Date otpExpiryDateTime) {
        this.otpExpiryDateTime = otpExpiryDateTime;
    }

    public int getRequestStatusCd() {
        return requestStatusCd;
    }

    public void setRequestStatusCd(int requestStatusCd) {
        this.requestStatusCd = requestStatusCd;
    }

    public int getParentRequestId() {
        return parentRequestId;
    }

    public void setParentRequestId(int parentRequestId) {
        this.parentRequestId = parentRequestId;
    }
}
