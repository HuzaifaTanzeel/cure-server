package com.example.demo.DTOMapper.PersonalProfiling;

import com.example.demo.CommonFramework.DTO.AddressDTO;
import com.example.demo.CommonFramework.Model.Address.*;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import com.example.demo.CommonFramework.Repositories.Address.*;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class AddressDTOMapper implements Function<GenPartyAddress, AddressDTO> {

    @Autowired
    private GenAddressRepository genAddressRepository;

    @Autowired
    private GenPartyRoleRepository genPartyRoleRepository;

    @Autowired
    private GenPartyAddressRepository genPartyAddressRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    private static final Logger logger = LoggerFactory.getLogger(AddressDTOMapper.class);


    @Transactional
    @Override
    public AddressDTO apply(GenPartyAddress genPartyAddress) {
//        if (genPartyAddress == null || genPartyAddress.getGenPartyRole() == null ||
//                genPartyAddress.getGenPartyRole().getGenPartyRoleId() == null ||
//                genPartyAddress.getAddress() == null) {
//            // Handle the case where one of the properties is null
//            // You can return a default DTO or throw an exception
//            return new AddressDTO(); // Adjust this line based on your requirements
//        }

        return new AddressDTO(
                genPartyAddress.getGenPartyRole().getGenPartyRoleId().getPartyId(),
                genPartyAddress.getGenPartyRole().getGenPartyRoleId().getPartyRoleTypeId(),
                genPartyAddress.getAddress().getAddressId(),
                genPartyAddress.getAddress().getAddrName(),
                genPartyAddress.getAddress().getAddrTypeCd(),
                genPartyAddress.getAddress().getCountryId(),
                genPartyAddress.getAddress().getCountry().getLongName(),
                genPartyAddress.getAddress().getProvinceId(),
                genPartyAddress.getAddress().getProvinces().getProvinceName(),
                genPartyAddress.getAddress().getCityId(),
                genPartyAddress.getAddress().getCity().getCityName(),
                genPartyAddress.getAddress().getAddrLine1(),
                genPartyAddress.getAddress().getAddrLine2()
        );
    }

    @Transactional
    public GenPartyAddress mapToPartyAddress(AddressDTO addressDTO) {
        try {
            GenPartyRoleId genPartyRoleId = new GenPartyRoleId(addressDTO.getPartyId(),addressDTO.getPartyRoleTypeId());
            Optional<GenPartyRole> genPartyRole=genPartyRoleRepository.findById(genPartyRoleId);
            if(genPartyRole.isPresent()) {
                GenPartyRole savedRole = genPartyRole.get();

                //Creating address entry
                GenAddress genAddress = new GenAddress(
                        addressDTO.getAddrName(),
                        addressDTO.getAddrTypeCd(),
                        addressDTO.getCountryId(),
                        addressDTO.getProvinceId(),
                        addressDTO.getCityId(),
                        addressDTO.getAddrLine1(),
                        addressDTO.getAddrLine2()
                );
                Optional<Countries> country=countryRepository.findById(addressDTO.getCountryId());
                Optional<Provinces> province=provinceRepository.findById(addressDTO.getCountryId());
                Optional<Cities> city=cityRepository.findById(addressDTO.getCountryId());

                //Saving address entry
                GenAddress savedAddress = genAddressRepository.save(genAddress);

                //Setting associations of country, province & city with address
                country.ifPresent(savedAddress::setCountry);
                province.ifPresent(savedAddress::setProvinces);
                city.ifPresent(savedAddress::setCity);

                //Linking address with Party & role
                GenPartyAddress genPartyAddress = new GenPartyAddress(
                        new GenPartyAddressId(
                                genPartyRoleId,
                                savedAddress.getAddressId()
                        )
                );
                genPartyAddress.setAddress(savedAddress);
                savedRole.addPartyAddress(genPartyAddress);
                genPartyRoleRepository.save(savedRole);
                return genPartyAddressRepository.save(genPartyAddress);
            }
            else {
                logger.error("GenPartyRole not found for ID: {}", genPartyRoleId);
                return null; // or throw an exception
            }
            // Set other properties as needed


        } catch (Exception e) {
            logger.error("Error creating party address", e);
            return null;
        }
    }

}
