package com.example.demo.CommonFramework.Services;

import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRepository;
import com.example.demo.CommonFramework.Repositories.GenPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
    private final GenPartyRepository genPartyRepository;

    private final GenPersonRepository genPersonRepository;

    @Autowired
    public PartyService(GenPartyRepository genPartyRepository, GenPersonRepository genPersonRepository) {
        this.genPartyRepository = genPartyRepository;
        this.genPersonRepository = genPersonRepository;
    }

//    public int generateNextRequestId() {
//        Integer maxRequestId = this.genPartyRepository.findMaxPartyId();
//        if (maxRequestId == null) {
//            maxRequestId = 0;
//        } else {
//            maxRequestId = maxRequestId;
//        }
//
//        maxRequestId = maxRequestId + 1;
//        return maxRequestId;
//    }

//    @Transactional
//    public void savePartyAndPerson(PartyPersonRequest request) {
//        int nextRequestId = this.generateNextRequestId();
//        System.out.println("My pleasure " + nextRequestId);
//        GenParty party = new GenParty();
//        party.setPartyId(nextRequestId);
//        party.setPartyTypeCd(request.getPartyTypeCD());
//        this.genPartyRepository.save(party);
//        GenPerson person = new GenPerson();
//        person.setFirstName(request.getFirstName());
//        person.setLastName(request.getLastName());
//        person.setParty(party);
//        person.toString();
//        this.genPersonRepository.save(person);
//    }
}
