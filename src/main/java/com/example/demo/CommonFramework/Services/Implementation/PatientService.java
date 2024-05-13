package com.example.demo.CommonFramework.Services.Implementation;

import com.example.demo.CommonFramework.Model.AdmPatient;
import com.example.demo.CommonFramework.Model.Contact.GenContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPointId;
import com.example.demo.CommonFramework.Model.GenPerson;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleType;
import com.example.demo.CommonFramework.Model.RegistrationRequest;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccount;
import com.example.demo.CommonFramework.Model.UAM.UAMUserAccountProfile;
import com.example.demo.CommonFramework.Enumerators.ContactPointType;
import com.example.demo.CommonFramework.Enumerators.PartyType;
import com.example.demo.CommonFramework.Repositories.AdmPatientRepository;
import com.example.demo.CommonFramework.Repositories.AdmPractitionerRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenContactPointRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenPartyContatctPointRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleTypeRepository;
import com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration.UserAccountProfileService;
import com.example.demo.CommonFramework.Services.Implementation.AccountANDRegistration.UserAccountService;
import com.example.demo.CommonFramework.Services.Implementation.Person.PersonServiceService;
import com.example.demo.CommonFramework.Services.Implementation.ProfileDetails.PartyContactPointService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private final AdmPatientRepository admPatientRepository;

    @Autowired
    private final UserAccountService userAccountService;

    @Autowired
    private final PersonServiceService personService;

    @Autowired
    private final UserAccountProfileService userAccountProfileService;
    @Autowired
    private final GenPartyRoleTypeRepository genPartyRoleTypeRepository;
    @Autowired
    private final AdmPractitionerRepository admPractitionerRepository;

    @Autowired
    private final PartyContactPointService partyContactPointService;

    @Autowired
    private GenContactPointRepository genContactPointRepository;

    @Autowired
    private final GenPartyRoleRepository genPartyRoleRepository;

    @Autowired
    private final GenPartyContatctPointRepository genPartyContatctPointRepository;


    public PatientService(AdmPatientRepository admPatientRepository, UserAccountService userAccountService, PersonServiceService personService, UserAccountProfileService userAccountProfileService, GenPartyRoleTypeRepository genPartyRoleTypeRepository, AdmPractitionerRepository admPractitionerRepository, PartyContactPointService partyContactPointService, GenPartyRoleRepository genPartyRoleRepository, GenPartyContatctPointRepository genPartyContatctPointRepository) {
        this.admPatientRepository = admPatientRepository;
        this.userAccountService = userAccountService;
        this.personService = personService;
        this.userAccountProfileService = userAccountProfileService;
        this.genPartyRoleTypeRepository = genPartyRoleTypeRepository;
        this.admPractitionerRepository = admPractitionerRepository;
        this.partyContactPointService = partyContactPointService;
        this.genPartyRoleRepository = genPartyRoleRepository;
        this.genPartyContatctPointRepository = genPartyContatctPointRepository;
    }

    public AdmPatient createPatient(GenPerson savedPerson) {
        AdmPatient patient = new AdmPatient(savedPerson, this.getRoleType("Patient"));
        System.out.println(this.getRoleType("Patient"));
        return admPatientRepository.save(patient);
    }

    @Transactional
    public void createPatientProfile(RegistrationRequest registrationRequest) {

        UAMUserAccount savedUserAccount = userAccountService.createUserAccount(registrationRequest);

        GenPerson savedPerson = personService.addPersonalDetails(PartyType.PERSON.getCode(), registrationRequest);

        UAMUserAccountProfile savedUserAccountProfile = userAccountProfileService.createAccountProfile(savedUserAccount,savedPerson);


        AdmPatient savedPatient = createPatient(savedPerson);
        this.createContactPoint(savedPatient.getGenPartyRole(), registrationRequest);


    }

    public GenPartyRoleType getRoleType(String roleName) {
        return genPartyRoleTypeRepository.findByRoleName(roleName);
    }


    public GenPartyContactPoint createContactPoint(GenPartyRole genPartyRole, RegistrationRequest registrationRequest) {

        GenContactPoint contactPoint = new GenContactPoint(
                registrationRequest.getEmail(),
                ContactPointType.EMAIL.getCode()
        );

        GenContactPoint savedContactPoint = genContactPointRepository.save(contactPoint);

        GenPartyContactPoint genPartyContactPoint = new GenPartyContactPoint(
                new GenPartyContactPointId(

                        genPartyRole.getGenPartyRoleId(),
                        savedContactPoint.getContactPointId()
                )
        );
        genPartyRole.addPartyContactPoint(genPartyContactPoint);
        genPartyRoleRepository.save(genPartyRole);
        return genPartyContatctPointRepository.save(genPartyContactPoint);
    }
}