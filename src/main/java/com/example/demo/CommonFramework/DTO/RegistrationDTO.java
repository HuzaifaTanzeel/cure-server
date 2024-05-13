package com.example.demo.CommonFramework.DTO;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
    private String licenseNo;
    private Long partyRoleTypeId;


}
