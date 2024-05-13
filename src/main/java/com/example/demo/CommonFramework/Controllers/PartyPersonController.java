//package com.example.demo.CommonFramework.Controllers;
//
//import com.example.demo.CommonFramework.Services.PartyService;
//import com.example.demo.CommonFramework.Services.PractitionerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class PartyPersonController {
//    private final PartyService partyservice;
//    private final PractitionerService practitionerService;
//
//    @Autowired
//    public PartyPersonController(PartyService partyservice, PractitionerService practitionerService) {
//        this.partyservice = partyservice;
//        this.practitionerService = practitionerService;
//    }
//
//    @PostMapping({"/createPractitioner"})
//    public void savePractitioner(@RequestBody PartyPersonRequest request) {
//        this.practitionerService.savePractitioner();
//        System.out.println("HIIII " + request.getLastName() + "  sfdfd");
//    }
//
////    @PostMapping({"/createPartyAndPerson"})
////    public void createPartyAndPerson(@RequestBody PartyPersonRequest request) {
////        this.partyservice.savePartyAndPerson(request);
////        String personName = request.getLastName();
////        System.out.println("HIIII " + request.getLastName() + "  sfdfd");
////    }
//}
