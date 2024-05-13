package com.example.demo.CommonFramework.Services.Interface.Person;

import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Model.RegistrationRequest;

public interface PersonServiceInterface {

    public GenPerson addPersonalDetails(int PartyTypeCD, RegistrationRequest registrationRequest);
}
