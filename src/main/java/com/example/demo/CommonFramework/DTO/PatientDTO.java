package com.example.demo.CommonFramework.DTO;

import com.example.demo.CommonFramework.Enumerators.GenderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatientDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String cureID;

    private String licenseNo;
    private GenderType genderType;

    private List<AddressDTO> personAddresses;
}
