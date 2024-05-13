package com.example.demo.CommonFramework.Controllers;

import com.example.demo.CommonFramework.DTO.RegistrationDTO;
import com.example.demo.CommonFramework.DTO.PartyVerificationDTO;
import com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practitioner")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

//    @PostMapping("/create")
//    public ResponseEntity<String> createEmployee(@RequestBody PractitionerRegistrationDTO practitionerRegistrationDTO) {
//        try {
//            practitionerRegistrationService.createPractitionerRegistrationRequest(practitionerRegistrationDTO);
//
//            practitionerRegistrationService.generateAndUpdateOTP();
//            return new ResponseEntity<>("Practitioner created successfully",HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error creating practitioner: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createEmployee(@RequestBody RegistrationDTO registrationDTO) {
        try {

            int requestId = registrationService.createRegistrationRequest(registrationDTO);

            registrationService.generateAndUpdateOTP(requestId);

            // Return the requestId in the response body
            return new ResponseEntity<>(requestId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/generate-otp")
//    public ResponseEntity<String> generateAndUpdateOtp() {
//        practitionerRegistrationService.generateAndUpdateOTP();
//        return new ResponseEntity<>("OTP generated and updated successfully.".concat(" "+practitionerRegistrationService.getOtpValue()), HttpStatus.OK);
//    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestBody PartyVerificationDTO partyVerificationDTO) {
        boolean isValid = registrationService.validateOTP(partyVerificationDTO);

        if (isValid) {
            return new ResponseEntity<>("OTP validated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("OTP validation failed.", HttpStatus.BAD_REQUEST);
        }
    }


}
