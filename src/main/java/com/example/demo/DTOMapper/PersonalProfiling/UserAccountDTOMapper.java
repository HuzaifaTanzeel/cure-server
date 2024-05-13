package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.UserLoginDTO;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccountProfile;
import com.example.demo.CommonFramework.Repositories.UAM.UAMUserAccountProfileRepository;
import com.example.demo.CommonFramework.Repositories.UAM.UAMUserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserAccountDTOMapper implements Function<UAMUserAccount, UserLoginDTO> {

    @Autowired
    private UAMUserAccountRepository uamUserAccountRepository;

    @Autowired
    private UAMUserAccountProfileRepository uamUserAccountProfileRepository;
    @Override
    public UserLoginDTO apply(UAMUserAccount uamUserAccount) {
        return new UserLoginDTO(
                uamUserAccount.getUserLogin(),
                uamUserAccount.getPassword(),
                uamUserAccount.getUserAccountProfile().getPartyId(),
                uamUserAccount.getPartyRoleTypeId()
        );
    }

    public UAMUserAccount mapToUserAccount(UserLoginDTO userLoginDTO){
        UAMUserAccount userAccount=new UAMUserAccount(
                userLoginDTO.getUserLogin(),
                userLoginDTO.getPassword(),
                userLoginDTO.getPartyRoleTypeId());

        UAMUserAccount savedUserAccount=uamUserAccountRepository.save(userAccount);


        UAMUserAccountProfile userAccountProfile=new UAMUserAccountProfile();
        userAccountProfile.setUserAccount(userAccount);
        savedUserAccount.setUserAccountProfile(userAccountProfile);



        // Save in the correct order

        uamUserAccountProfileRepository.save(userAccountProfile);

        return uamUserAccountRepository.save(savedUserAccount);
    }
}
