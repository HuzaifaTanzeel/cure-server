package com.example.demo.CommonFramework.Controllers;

import com.example.demo.CommonFramework.DTO.HCProviderOrgDTO;
import com.example.demo.CommonFramework.DTO.OrganizationDTO;
import com.example.demo.CommonFramework.DTO.PartyIdentityDTO;
import com.example.demo.CommonFramework.Services.Implementation.HCProviderService;
import com.example.demo.CommonFramework.Services.Implementation.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practice")
public class PracticeController {

    @Autowired
    private OrgService orgService;

    @Autowired
    private HCProviderService hcProviderService;

//    @PostMapping("/createPractice")
//    public ResponseEntity<OrganizationDTO> createAddress(@RequestBody OrganizationDTO organizationDTO) {
//        try {
//            OrganizationDTO savedOrganization = orgService.createPractice(organizationDTO);
//            return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/getPractice")
//    public ResponseEntity<HCProviderOrgDTO> getAddress(@PathVariable Long partyId, @PathVariable Long partyRoleTypeId) {
//        try {
//            HCProviderOrgDTO hcProviderOrgDTO = hcProviderService.createPractice();
//            return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/createPractice")
    public ResponseEntity<HCProviderOrgDTO> createPractice(@RequestBody HCProviderOrgDTO hcProviderOrgDTO) {
        try {
            HCProviderOrgDTO savedHCProviderOrg = hcProviderService.createPractice(hcProviderOrgDTO);
            return new ResponseEntity<>(savedHCProviderOrg, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPractice/{partyId}/{partyRoleTypeId}")
    public ResponseEntity<HCProviderOrgDTO> getPractice(@PathVariable Long partyId,@PathVariable Long partyRoleTypeId) {
        try {

            HCProviderOrgDTO savedHCProviderOrg = hcProviderService.getPractice(partyId,partyRoleTypeId);
            return new ResponseEntity<>(savedHCProviderOrg, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
