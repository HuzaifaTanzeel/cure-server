package com.example.demo.CommonFramework.Services.Interface.AccountANDRegistration;

import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccountProfile;

public interface UserAccountProfileInterface {

    public UAMUserAccountProfile createAccountProfile(UAMUserAccount savedUserAccount, GenPerson savedPerson);
}
