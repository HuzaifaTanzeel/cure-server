package com.example.demo.CommonFramework.Controllers;

import com.example.demo.CommonFramework.DTO.CountryDTO;
import com.example.demo.CommonFramework.DTO.IdentityTypeDTO;
import com.example.demo.CommonFramework.Services.Implementation.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private IdentityService identityService;

    @GetMapping("/identity")
    public ResponseEntity<List<IdentityTypeDTO>> getCountries(){
        List<IdentityTypeDTO> identityTypes = identityService.getIdentityTypes();
        return new ResponseEntity<>(identityTypes, HttpStatus.OK);
    }
}