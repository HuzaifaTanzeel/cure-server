package com.example.demo.CommonFramework.Services.Interface.ProfileDetails;

import com.example.demo.CommonFramework.Model.AdmPractitioner;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPoint;
import com.example.demo.CommonFramework.Model.RegistrationRequest;

public interface PartyContactPointInterface {

    public GenPartyContactPoint createContactPoint(RegistrationRequest registrationRequest, AdmPractitioner admPractitioner);
}