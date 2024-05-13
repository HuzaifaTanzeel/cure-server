package com.example.demo.CommonFramework.Controllers;

import com.example.demo.CommonFramework.DTO.*;
import com.example.demo.CommonFramework.Services.Implementation.GenPartyRoleService;
import com.example.demo.CommonFramework.Services.Implementation.Person.PersonServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/party")
public class PartyProfiling {


    @Autowired
    private GenPartyRoleService genPartyRoleService;

    @Autowired
    private PersonServiceService personService;


    @PostMapping("/createAddress")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        try {

            AddressDTO saveAddress = genPartyRoleService.createPartyAddress(addressDTO);
            // Return the requestId in the response body
            return new ResponseEntity<>(saveAddress, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAddresses/{partyId}/{partyRoleTypeId}")
    public ResponseEntity<List<AddressDTO>> getAddresses(@PathVariable Long partyId,@PathVariable Long partyRoleTypeId) {
        try {
            List<AddressDTO> addresses = genPartyRoleService.getPartyAddresses(partyId,partyRoleTypeId);
            // Return the requestId in the response body
            return new ResponseEntity<>(addresses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/PersonDetails/{partyId}")
    public ResponseEntity<PersonDTO> getPersonDetails(@PathVariable Long partyId) {
        try {
            PersonDTO personDetails=personService.getPersonalDetails(partyId);
            return new ResponseEntity<>(personDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/createQualification")
    public ResponseEntity<QualificationDTO> createQualification(@RequestBody QualificationDTO qualificationDTO) {
        try {

            QualificationDTO savedQualification = personService.createQualification(qualificationDTO);
            // Return the requestId in the response body
            return new ResponseEntity<>(savedQualification, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getQualifications/{partyId}")
    public ResponseEntity<List<QualificationDTO>> getQualifications(@PathVariable Long partyId) {
        try {

            List<QualificationDTO> savedQualification = personService.getAllQualifications(partyId);
            // Return the requestId in the response body
            return new ResponseEntity<>(savedQualification, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createIdentity")
    public ResponseEntity<PartyIdentityDTO> createIdentity(@RequestBody PartyIdentityDTO partyIdentityDTO) {
        try {

            PartyIdentityDTO savedIdentity = genPartyRoleService.createPartyIdentity(partyIdentityDTO);
            // Return the requestId in the response body
            return new ResponseEntity<>(savedIdentity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getIdentities/{partyId}/{partyRoleTypeId}")
    public ResponseEntity<List<PartyIdentityDTO>> getIdentities(@PathVariable Long partyId,@PathVariable Long partyRoleTypeId) {
        try {

            List<PartyIdentityDTO> savedIdentity = genPartyRoleService.getPartyIdentities(partyId,partyRoleTypeId);
            // Return the requestId in the response body
            return new ResponseEntity<>(savedIdentity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{partyId}")
    public ResponseEntity<PersonDTO> updatePersonDetails(
            @PathVariable Long partyId,
            @RequestBody PersonDTO updatedPersonDetails) {
        try {
            PersonDTO updatedDetails = personService.updatePersonalDetails(partyId, updatedPersonDetails);
            return ResponseEntity.ok(updatedDetails);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/updateAddress/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO) {
        try {
            AddressDTO updatedAddressDTO = genPartyRoleService.updateAddress(addressId, addressDTO);
            if (updatedAddressDTO != null) {
                return new ResponseEntity<>(updatedAddressDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getContactPoints/{partyId}/{partyRoleTypeId}")
    public ResponseEntity<List<ContactPointDTO>> getContactPoints(@PathVariable Long partyId, @PathVariable Long partyRoleTypeId) {
        try {
            List<ContactPointDTO> contacts = genPartyRoleService.getContactPoints(partyRoleTypeId, partyRoleTypeId);
            // Return the requestId in the response body
            return new ResponseEntity<>(contacts, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/addContactPoint")
    public ResponseEntity<ContactPointDTO> addContactPoints(@RequestBody ContactPointDTO contactPointDTO) {
        try {
            ContactPointDTO savedContact = genPartyRoleService.addContactPoint(contactPointDTO);
            // Return the requestId in the response body
            return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateContact/{contactPointId}")
    public ResponseEntity<ContactPointDTO> updateContact(@PathVariable Long contactPointId, @RequestBody ContactPointDTO contactPointDTO) {
        try {
            ContactPointDTO updatedContactDTO = genPartyRoleService.updateContact(contactPointId,contactPointDTO);
            if (updatedContactDTO != null) {
                return new ResponseEntity<>(updatedContactDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }









}
