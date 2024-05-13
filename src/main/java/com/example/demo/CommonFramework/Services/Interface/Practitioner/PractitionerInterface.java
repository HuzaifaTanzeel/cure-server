package com.example.demo.CommonFramework.Services.Interface.Practitioner;

import com.example.demo.CommonFramework.Model.AdmPractitioner;
import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Model.RegistrationRequest;

public interface PractitionerInterface {

    AdmPractitioner createPractitioner(GenPerson savedPerson);
    public void createPractitionerProfile(RegistrationRequest registrationRequest);



}
