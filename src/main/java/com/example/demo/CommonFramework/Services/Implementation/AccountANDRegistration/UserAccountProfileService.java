package com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration;

import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccountProfile;
import com.example.demo.CommonFramework.Repositories.UAM.UAMUserAccountProfileRepository;
import com.example.demo.CommonFramework.Repositories.UAM.UAMUserAccountRepository;
import com.example.demo.CommonFramework.Services.Interface.AccountANDRegistration.UserAccountProfileInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountProfileService implements UserAccountProfileInterface {

    @Autowired
    private final UAMUserAccountProfileRepository uamUserAccountProfileRepository;

    @Autowired
    private final UAMUserAccountRepository uamUserAccountRepository;

    public UserAccountProfileService(UAMUserAccountProfileRepository uamUserAccountProfileRepository, UAMUserAccountRepository uamUserAccountRepository) {
        this.uamUserAccountProfileRepository = uamUserAccountProfileRepository;
        this.uamUserAccountRepository = uamUserAccountRepository;
    }

    @Transactional
    @Override
    public UAMUserAccountProfile createAccountProfile(UAMUserAccount savedUserAccount, GenPerson savedPerson) {
        UAMUserAccountProfile userAccountProfile = new UAMUserAccountProfile();
        // Set other properties...

        // Linking AccountProfile with UserAccount
        userAccountProfile.setUserAccount(savedUserAccount);
        savedUserAccount.setUserAccountProfile(userAccountProfile);


        // Linking AccountProfile with GenParty
        userAccountProfile.setPartyId(savedPerson.getPartyId());
        userAccountProfile.setGenParty(savedPerson);
        savedPerson.addAccountProfile(userAccountProfile);


        // Save in the correct order
        uamUserAccountRepository.save(savedUserAccount);
        uamUserAccountProfileRepository.save(userAccountProfile);

        return userAccountProfile;
    }

}
