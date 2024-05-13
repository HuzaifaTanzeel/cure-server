package com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration;

import com.example.demo.CommonFramework.DTO.RegistrationDTO;
import com.example.demo.CommonFramework.DTO.PartyVerificationDTO;
import com.example.demo.CommonFramework.Model.RegistrationRequest;
import com.example.demo.CommonFramework.Features.OTPGenerator;
import com.example.demo.CommonFramework.Repositories.Registration.RegistrationRequestRepository;
import com.example.demo.CommonFramework.Services.Implementation.PatientService;
import com.example.demo.CommonFramework.Services.Implementation.Practitioner.PractitionerService;
import com.example.demo.CommonFramework.Services.Interface.AccountANDRegistration.RegistrationServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegistrationService implements RegistrationServiceInterface {
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private final PractitionerService practitionerService;

    @Autowired
    private final PatientService patientService;
    private String otpValue;

    public RegistrationService(PractitionerService practitionerService, PatientService patientService) {
        this.practitionerService = practitionerService;
        this.patientService = patientService;
    }

    public String getOtpValue() {
        return otpValue;
    }

    @Override
    public String generateOTP()
    {
        OTPGenerator otpGenerator = new OTPGenerator();
        otpGenerator.sendOTPEmail("k201054@nu.edu.pk","txlz zgik kvbs umjb","k200305@nu.edu.pk");
        this.otpValue = otpGenerator.getOtp();
        return otpGenerator.getOtp();
    }

    @Override
    public boolean validateOTP(PartyVerificationDTO partyVerificationDTO) {
        Date currentTime = new Date();
        System.out.println("Current Time: " + currentTime + " OTP: " + partyVerificationDTO.getOTP());

        Optional<RegistrationRequest> requestOptional = registrationRequestRepository.findByRequestId(partyVerificationDTO.getRequestId());


        if (requestOptional.isPresent()) {
            RegistrationRequest registrationRequest = requestOptional.get();

            // Log the retrieved OtpExpiryDateTime
            System.out.println("OtpExpiryDateTime: " + registrationRequest.getOtpExpiryDateTime());

            if (currentTime.before(registrationRequest.getOtpExpiryDateTime())
                    && Objects.equals(partyVerificationDTO.getOTP(), registrationRequest.getOtp())) {


                //userAccountService.createUserAccount(registrationRequestPractitioner);

                if(registrationRequest.getPartyRoleTypeId()==1){
                    practitionerService.createPractitionerProfile(registrationRequest);
                }
                else if(registrationRequest.getPartyRoleTypeId()==2){
                    patientService.createPatientProfile(registrationRequest);
                }

                //registrationRequestPractitionerRepository.delete(registrationRequestPractitioner);
                System.out.println("OTP validated successfully.");
                return true;
            } else {
                System.out.println("OTP has expired or doesn't match.");
                return false;
            }
        } else {
            System.out.println("No matching OTP found or OTP has expired.");
            return false;
        }
    }

    @Override
    @Transactional
    public void generateAndUpdateOTP(int requestId){
        String newOtpValue = this.generateOTP();

        //Optional<RegistrationRequestPractitioner> mostRecentOtpOptional = registrationRequestPractitionerRepository.findTopByOrderByOtpExpiryDateTimeDesc();
        Optional<RegistrationRequest> requestOptional = registrationRequestRepository.findByRequestId(requestId);

        requestOptional.ifPresent(request -> {
            request.setOtp(newOtpValue);
            request.setOtpExpiryDateTime(calculateNewExpiryTime());
            registrationRequestRepository.save(request);
        });
    }

    @Override
    public Date calculateNewExpiryTime(){
        return new Date(System.currentTimeMillis() + 180000);
    }


    @Transactional
    @Override
    public int createRegistrationRequest(RegistrationDTO registrationDTO)
    {
        registrationDTO.setPassword(registrationDTO.getPassword());
        RegistrationRequest registrationRequestPractitioner=new RegistrationRequest(
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getEmail(),
                registrationDTO.getPassword(),
                registrationDTO.getMobile(),
                registrationDTO.getLicenseNo(),
                registrationDTO.getPartyRoleTypeId()
        );
        // Save the entity
        RegistrationRequest savedRequest = registrationRequestRepository.save(registrationRequestPractitioner);

        // Return the requestId
        return savedRequest.getRequestId();
    }

}