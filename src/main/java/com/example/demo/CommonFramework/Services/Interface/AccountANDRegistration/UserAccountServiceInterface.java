package com.example.demo.CommonFramework.Services.Interface.AccountANDRegistration;

import com.example.demo.CommonFramework.Model.RegistrationRequest;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;

public interface UserAccountServiceInterface {

    public UAMUserAccount createUserAccount(RegistrationRequest registrationRequest);
}
