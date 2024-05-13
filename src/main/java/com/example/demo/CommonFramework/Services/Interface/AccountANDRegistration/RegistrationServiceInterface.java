package com.example.demo.CommonFramework.Services.Interface.AccountANDRegistration;

import com.example.demo.CommonFramework.DTO.RegistrationDTO;
import com.example.demo.CommonFramework.DTO.PartyVerificationDTO;

import java.util.Date;

public interface RegistrationServiceInterface {
    public String generateOTP();
    public boolean validateOTP(PartyVerificationDTO partyVerificationDTO);
    public void generateAndUpdateOTP(int requestId);

    public Date calculateNewExpiryTime();
    public int createRegistrationRequest(RegistrationDTO registrationDTO);
}
