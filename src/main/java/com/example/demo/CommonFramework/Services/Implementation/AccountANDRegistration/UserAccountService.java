package com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration;

import com.example.demo.CommonFramework.DTO.UserLoginDTO;
import com.example.demo.CommonFramework.Model.*;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleTypeRepository;
import com.example.demo.CommonFramework.Repositories.UAM.UAMUserAccountProfileRepository;
import com.example.demo.CommonFramework.Repositories.UAM.UAMUserAccountRepository;
import com.example.demo.CommonFramework.Services.Implementation.GenPartyRoleService;
import com.example.demo.CommonFramework.Services.Implementation.ProfileDetails.PartyContactPointService;
import com.example.demo.CommonFramework.Services.Interface.AccountANDRegistration.UserAccountServiceInterface;
import com.example.demo.DTOMapper.PersonalProfiling.UserAccountDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService implements UserAccountServiceInterface {

    @Autowired
    private UAMUserAccountRepository uamUserAccountRepository;


    @Autowired
    private GenPartyRoleTypeRepository genPartyRoleTypeRepository;

    @Autowired
    private GenPartyRoleRepository genPartyRoleRepository;

    @Autowired
    private GenPartyRepository genPartyRepository;

    @Autowired
    private UAMUserAccountProfileRepository uamUserAccountProfileRepository;


    @Autowired
    private PartyContactPointService partyContactPointService;

    @Autowired
    private GenPartyRoleService genPartyRoleService;

    @Autowired
    private UserAccountDTOMapper userAccountDTOMapper;
    @Transactional
    @Override
    public UAMUserAccount createUserAccount(RegistrationRequest registrationRequest) {
        UAMUserAccount userAccount = new UAMUserAccount(
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                3001L,
                registrationRequest.getPartyRoleTypeId()
        );
        return uamUserAccountRepository.save(userAccount);
    }

    @Transactional
    public UserLoginDTO authenticate(UserLoginDTO userLoginDTO){

        UAMUserAccount userAccount = uamUserAccountRepository.findByUserLogin(userLoginDTO.getUserLogin());

        //UAMUserAccount userAccount = userAccountDTOMapper.mapToUserAccount(userLoginDTO);
        if (userAccount != null && userAccount.getPassword().equals(userLoginDTO.getPassword())) {
            System.out.println(userAccount.getUserLogin());
            System.out.println(userAccount.getPassword());

            System.out.println(userLoginDTO.getUserLogin());
            System.out.println(userLoginDTO.getPassword());


            UAMUserAccount userAccount1 = userAccountDTOMapper.mapToUserAccount(userLoginDTO);

            return userAccountDTOMapper.apply(userAccount1);
        }
        System.out.println(userAccount.getUserLogin());
        System.out.println(userAccount.getPassword());

        System.out.println(userLoginDTO.getUserLogin());
        System.out.println(userLoginDTO.getPassword());

        return null; // Authentication failed


    }

}
