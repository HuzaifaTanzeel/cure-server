package com.example.demo.CommonFramework.Controllers;

import com.example.demo.CommonFramework.DTO.FacilityDTO;
import com.example.demo.CommonFramework.DTO.HCProviderOrgDTO;
import com.example.demo.CommonFramework.Services.Implementation.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @PostMapping("/createFacility")
    public ResponseEntity<FacilityDTO> createFacility(@RequestBody FacilityDTO facilityDTO) {
        try {
            FacilityDTO savedFacilityDTO = facilityService.createFacility(facilityDTO);
            return new ResponseEntity<>(savedFacilityDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getFacility/{partyId}/{partyRoleTypeId}")
    public ResponseEntity<List<FacilityDTO>> getFacility(@PathVariable Long partyId, @PathVariable Long partyRoleTypeId) {
        try {
            List<FacilityDTO> savedFacilityDTO = facilityService.getFacility(partyId,partyRoleTypeId);
            return new ResponseEntity<>(savedFacilityDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getFacilityById/{facilityId}")
    public ResponseEntity<FacilityDTO> getFacilityById(@PathVariable Long facilityId) {
        try {
            FacilityDTO savedFacilityDTO = facilityService.getFacilityById(facilityId);
            return new ResponseEntity<>(savedFacilityDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{facilityId}")
    public ResponseEntity<String> deleteFacility(@PathVariable Long facilityId) {
        try {
            facilityService.deleteFacility(facilityId);
            return new ResponseEntity<>("Facility deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete facility", HttpStatus.NOT_FOUND);
        }
    }
}
