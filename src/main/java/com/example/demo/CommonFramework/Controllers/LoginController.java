package com.example.demo.CommonFramework.Controllers;

import com.example.demo.CommonFramework.DTO.UserLoginDTO;
import com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserAccountService userAccountService;
    @PostMapping("/authenticateUser")
    public ResponseEntity<UserLoginDTO> createFacility(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            UserLoginDTO authenticatedUser = userAccountService.authenticate(userLoginDTO);
            if (authenticatedUser != null) {
                return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
