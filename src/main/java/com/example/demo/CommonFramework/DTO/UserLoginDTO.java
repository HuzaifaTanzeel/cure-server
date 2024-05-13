package com.example.demo.CommonFramework.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginDTO {

    private String userLogin;

    private String password;

    @JsonIgnore
    private Long partyId;

    @JsonIgnore
    private Long partyRoleTypeId;

    @JsonProperty("partyId")
    public Long getPartyId() {
        return partyId;
    }

    @JsonProperty("partyRoleTypeId")
    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public UserLoginDTO() {
    }

    public UserLoginDTO(String userLogin, Long partyId, Long partyRoleTypeId) {
        this.userLogin = userLogin;
        this.partyId=partyId;
        this.partyRoleTypeId=partyRoleTypeId;
    }



    public UserLoginDTO(String userLogin, String password,Long partyId,Long partyRoleTypeId) {
        this.userLogin = userLogin;
        this.password = password;
        this.partyId=partyId;
        this.partyRoleTypeId=partyRoleTypeId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
