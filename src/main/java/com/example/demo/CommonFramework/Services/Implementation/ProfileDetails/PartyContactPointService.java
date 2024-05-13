package com.example.demo.CommonFramework.Services.Implementation.ProfileDetails;

import com.example.demo.CommonFramework.Model.AdmPractitioner;
import com.example.demo.CommonFramework.Model.Contact.GenContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPointId;
import com.example.demo.CommonFramework.Model.RegistrationRequest;
import com.example.demo.CommonFramework.Enumerators.ContactPointType;
import com.example.demo.CommonFramework.Repositories.AdmPractitionerRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenContactPointRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenPartyContatctPointRepository;
import com.example.demo.CommonFramework.Services.Interface.ProfileDetails.PartyContactPointInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyContactPointService implements PartyContactPointInterface {

    @Autowired
    private final GenContactPointRepository genContactPointRepository;

    @Autowired
    private final GenPartyContatctPointRepository genPartyContatctPointRepository;

    @Autowired
    private AdmPractitionerRepository admPractitionerRepository;



    public PartyContactPointService(GenContactPointRepository genContactPointRepository, GenPartyContatctPointRepository genPartyContatctPointRepository) {
        this.genContactPointRepository = genContactPointRepository;
        this.genPartyContatctPointRepository=genPartyContatctPointRepository;
    }

    @Override
    public GenPartyContactPoint createContactPoint(RegistrationRequest registrationRequest, AdmPractitioner admPractitioner) {

        GenContactPoint contactPoint = new GenContactPoint(
                registrationRequest.getEmail(),
                ContactPointType.EMAIL.getCode()
        );

        GenContactPoint savedContactPoint = genContactPointRepository.save(contactPoint);

        GenPartyContactPoint genPartyContactPoint=new GenPartyContactPoint(
                new GenPartyContactPointId(

                        admPractitioner.getGenPartyRoleId(),
                        savedContactPoint.getContactPointId()
                )

//                admPractitioner,
//                savedContactPoint
        );

        //genPartyContactPoint.setGenPartyRole(admPractitioner.getGenPartyRole());

        admPractitioner.addPartyContactPoint(genPartyContactPoint);

        admPractitionerRepository.save(admPractitioner);

        return genPartyContatctPointRepository.save(genPartyContactPoint);
    }
}
