package com.example.demo.CommonFramework.Services.Implementation.Practitioner;

import com.example.demo.CommonFramework.Model.AdmPractitioner;
import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Model.RegistrationRequest;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccountProfile;
import com.example.demo.CommonFramework.Enumerators.PartyType;
import com.example.demo.CommonFramework.Repositories.Address.*;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.CommonFramework.Repositories.AdmPractitionerRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenContactPointRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenPartyContatctPointRepository;
import com.example.demo.CommonFramework.Repositories.GenPartyIdentityRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleTypeRepository;
import com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration.UserAccountProfileService;
import com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration.UserAccountService;
import com.example.demo.CommonFramework.Services.Implementation.GenPartyRoleService;
import com.example.demo.CommonFramework.Services.Implementation.Person.PersonServiceService;
import com.example.demo.CommonFramework.Services.Implementation.ProfileDetails.PartyContactPointService;
import com.example.demo.CommonFramework.Services.Interface.Practitioner.PractitionerInterface;
import com.example.demo.DTOMapper.PersonalProfiling.AddressDTOMapper;
import com.example.demo.DTOMapper.PersonalProfiling.ContactPointDTOMapper;
import com.example.demo.DTOMapper.PersonalProfiling.IdentityDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PractitionerService extends GenPartyRoleService implements PractitionerInterface {

    @Autowired
    private final UserAccountService userAccountService;
    @Autowired
    private final UserAccountProfileService userAccountProfileService;
    @Autowired
    private final PersonServiceService personService;
    @Autowired
    private final GenPartyRoleTypeRepository genPartyRoleTypeRepository;
    @Autowired
    private final AdmPractitionerRepository admPractitionerRepository;

    @Autowired
    private final PartyContactPointService partyContactPointService;

    @Autowired
    private GenContactPointRepository genContactPointRepository;

    @Autowired
    private final AdmPatientRepository admPatientRepository;

    @Autowired
    private AddressDTOMapper addressDTOMapper;


    public PractitionerService(UserAccountService userAccountService,
                               UserAccountProfileService userAccountProfileService,
                               PersonServiceService personService,
                               GenPartyRoleTypeRepository genPartyRoleTypeRepository,
                               AdmPractitionerRepository admPractitionerRepository,
                               PartyContactPointService partyContactPointService,
                               GenPartyRoleRepository genPartyRoleRepository,
                               CountryRepository countryRepository,
                               ProvinceRepository provinceRepository,
                               CityRepository cityRepository,
                               GenPartyContatctPointRepository genPartyContatctPointRepository,
                               GenContactPointRepository genContactPointRepository,
                               GenPartyAddressRepository genPartyAddressRepository,
                               GenPartyIdentityRepository genPartyIdentityRepository,
                               AddressDTOMapper addressDTOMapper,
                               IdentityDTOMapper identityDTOMapper,
                               GenAddressRepository genAddressRepository,
                               ContactPointDTOMapper contactPointDTOMapper, AdmPatientRepository admPatientRepository) {

        super(genPartyRoleTypeRepository, genPartyRoleRepository, countryRepository, provinceRepository, cityRepository, genPartyContatctPointRepository, genContactPointRepository, genPartyAddressRepository, genPartyIdentityRepository, addressDTOMapper, identityDTOMapper, genAddressRepository, contactPointDTOMapper);
        this.userAccountService = userAccountService;
        this.userAccountProfileService = userAccountProfileService;
        this.personService = personService;
        this.genPartyRoleTypeRepository = genPartyRoleTypeRepository;
        this.admPractitionerRepository = admPractitionerRepository;
        this.partyContactPointService = partyContactPointService;
        this.admPatientRepository = admPatientRepository;

    }

    @Override
    public AdmPractitioner createPractitioner(GenPerson savedPerson) {
        AdmPractitioner practitioner = new AdmPractitioner(savedPerson, this.getRoleType("Practitioner"));
        System.out.println(this.getRoleType("Practitioner"));
        return admPractitionerRepository.save(practitioner);
    }



    //GenPartyContactPoint savedContactPoint =partyContactPointService.createContactPoint(registrationRequestPractitioner,savedPractitioner);
    @Transactional
    public void createPractitionerProfile(RegistrationRequest registrationRequest) {

        UAMUserAccount savedUserAccount = userAccountService.createUserAccount(registrationRequest);


        GenPerson savedPerson = personService.addPersonalDetails(PartyType.PERSON.getCode(), registrationRequest);

        UAMUserAccountProfile savedUserAccountProfile = userAccountProfileService.createAccountProfile(savedUserAccount,savedPerson);
        AdmPractitioner savedPractitioner = createPractitioner(savedPerson);
        this.createContactPoint(savedPractitioner.getGenPartyRole(), registrationRequest);



//    @Transactional
//    @Override
//    public void createPatientProfile(RegistrationRequestPractitioner registrationRequestPractitioner){
//
//        UAMUserAccount savedUserAccount=userAccountService.createUserAccount(registrationRequestPractitioner);
//
//        GenPerson savedPerson = personService.addPersonalDetails(PartyType.PERSON.getCode(),registrationRequestPractitioner);
//
//        UAMUserAccountProfile savedUserAccountProfile = userAccountProfileService.createAccountProfile(savedUserAccount,savedPerson);
//
//        AdmPractitioner savedPractitioner = createPractitioner(savedPerson);
//
//        this.createContactPoint(savedPractitioner.getGenPartyRole(),registrationRequestPractitioner);
//        //GenPartyContactPoint savedContactPoint =partyContactPointService.createContactPoint(registrationRequestPractitioner,savedPractitioner);
//    }
    }
}