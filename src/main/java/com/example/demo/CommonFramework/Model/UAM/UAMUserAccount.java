package com.example.demo.CommonFramework.Model.UAM;

import com.example.demo.CommonFramework.Model.RegistrationRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UAM_USER_ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UAMUserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ACCOUNT_ID")
    private Long userAccountId;


    @Column
    private Long partyRoleTypeId;

    @Column(name = "USER_LOGIN", length = 50, nullable = true)
    private String userLogin;

    @Column(name = "PASSWORD", nullable = false)
    private String password;


    @Column(name = "USER_STATUS_CD", nullable = true)
    private Long userStatusCd;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_ACCOUNT_ID")  // This line has been changed
    private UAMUserAccountProfile userAccountProfile;

    @OneToMany(mappedBy = "userAccount")
    private List<RegistrationRequest> registrationRequests=new ArrayList<>();

    public UAMUserAccount(String userLogin, String password, Long partyRoleTypeId) {
        this.userLogin = userLogin;
        this.password = password;
        this.partyRoleTypeId=partyRoleTypeId;
    }



    public UAMUserAccount(String userLogin, String password, Long partyId, Long partyRoleTypeId) {
        this.userLogin = userLogin;
        this.password = password;
        this.partyRoleTypeId=partyRoleTypeId;
    }


}
