package com.example.demo.CommonFramework.Services.Implementation;

import com.example.demo.CommonFramework.DTO.AddressDTO;
import com.example.demo.CommonFramework.DTO.ContactPointDTO;
import com.example.demo.CommonFramework.DTO.PartyIdentityDTO;
import com.example.demo.CommonFramework.Model.*;
import com.example.demo.CommonFramework.Model.Address.*;
import com.example.demo.CommonFramework.Model.Contact.GenContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPoint;
import com.example.demo.CommonFramework.Model.Contact.GenPartyContactPointId;
import com.example.demo.CommonFramework.Model.Identity.GenPartyIdentity;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRole;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleId;
import com.example.demo.CommonFramework.Model.PartyRole.GenPartyRoleType;
import com.example.demo.CommonFramework.Enumerators.ContactPointType;
import com.example.demo.CommonFramework.Repositories.Address.*;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenContactPointRepository;
import com.example.demo.CommonFramework.Repositories.ContactPoint.GenPartyContatctPointRepository;
import com.example.demo.CommonFramework.Repositories.GenPartyIdentityRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleRepository;
import com.example.demo.CommonFramework.Repositories.PartyRoles.GenPartyRoleTypeRepository;
import com.example.demo.CommonFramework.Services.Interface.GenPartyRoleInterface;
import com.example.demo.DTOMapper.PersonalProfiling.AddressDTOMapper;
import com.example.demo.DTOMapper.PersonalProfiling.ContactPointDTOMapper;
import com.example.demo.DTOMapper.PersonalProfiling.IdentityDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenPartyRoleService implements GenPartyRoleInterface {

    @Autowired
    private final GenPartyRoleTypeRepository genPartyRoleTypeRepository;

    @Autowired
    private final GenPartyRoleRepository genPartyRoleRepository;

    @Autowired
    private final CountryRepository countryRepository;

    @Autowired
    private final ProvinceRepository provinceRepository;

    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final GenPartyContatctPointRepository genPartyContatctPointRepository;

    @Autowired
    private final GenContactPointRepository genContactPointRepository;

    @Autowired
    private final GenPartyAddressRepository genPartyAddressRepository;

    @Autowired
    private final GenPartyIdentityRepository genPartyIdentityRepository;

    @Autowired
    private final AddressDTOMapper addressDTOMapper;

    @Autowired
    private final IdentityDTOMapper identityDTOMapper;

    @Autowired
    private final GenAddressRepository genAddressRepository;

    @Autowired
    private final ContactPointDTOMapper contactPointDTOMapper;

    public GenPartyRoleService(GenPartyRoleTypeRepository genPartyRoleTypeRepository, GenPartyRoleRepository genPartyRoleRepository, CountryRepository countryRepository, ProvinceRepository provinceRepository, CityRepository cityRepository, GenPartyContatctPointRepository genPartyContatctPointRepository, GenContactPointRepository genContactPointRepository, GenPartyAddressRepository genPartyAddressRepository, GenPartyIdentityRepository genPartyIdentityRepository, AddressDTOMapper addressDTOMapper, IdentityDTOMapper identityDTOMapper, GenAddressRepository genAddressRepository, ContactPointDTOMapper contactPointDTOMapper) {
        this.genPartyRoleTypeRepository = genPartyRoleTypeRepository;
        this.genPartyRoleRepository = genPartyRoleRepository;
        this.countryRepository = countryRepository;
        this.provinceRepository = provinceRepository;
        this.cityRepository = cityRepository;
        this.genPartyContatctPointRepository = genPartyContatctPointRepository;
        this.genContactPointRepository = genContactPointRepository;
        this.genPartyAddressRepository = genPartyAddressRepository;
        this.genPartyIdentityRepository = genPartyIdentityRepository;
        this.addressDTOMapper = addressDTOMapper;
        this.identityDTOMapper = identityDTOMapper;
        this.genAddressRepository = genAddressRepository;
        this.contactPointDTOMapper = contactPointDTOMapper;
    }


    @Override
    public GenPartyRole assignPartyRole(GenParty savedGenParty){

        GenPartyRoleType savedRoleType = new GenPartyRoleType("Role Name", 302);
        genPartyRoleTypeRepository.save(savedRoleType);

        GenPartyRole genPartyRole = new GenPartyRole();
        genPartyRole.setGenPartyRoleId(new GenPartyRoleId(savedGenParty.getPartyId(), savedRoleType.getPartyRoleTypeId()));
        genPartyRole.setPartyId(savedGenParty);
        genPartyRole.setPartyRoleTypeId(savedRoleType);
        return genPartyRoleRepository.save(genPartyRole);
    }

    public GenPartyRoleType getRoleType(String roleName){
        return genPartyRoleTypeRepository.findByRoleName(roleName);
    }


    //@Override
    public GenPartyContactPoint createContactPoint(GenPartyRole genPartyRole,RegistrationRequest registrationRequestPractitioner) {

        GenContactPoint contactPoint = new GenContactPoint(
                registrationRequestPractitioner.getEmail(),
                ContactPointType.EMAIL.getCode()
        );

        GenContactPoint savedContactPoint = genContactPointRepository.save(contactPoint);

        GenPartyContactPoint genPartyContactPoint=new GenPartyContactPoint(
                new GenPartyContactPointId(

                        genPartyRole.getGenPartyRoleId(),
                        savedContactPoint.getContactPointId()
                )
        );
        genPartyRole.addPartyContactPoint(genPartyContactPoint);
        genPartyRoleRepository.save(genPartyRole);
        return genPartyContatctPointRepository.save(genPartyContactPoint);
    }

    @Transactional
    public AddressDTO createPartyAddress(AddressDTO addressDTO){
        GenPartyAddress genPartyAddress=addressDTOMapper.mapToPartyAddress(addressDTO);
        return addressDTOMapper.apply(genPartyAddress);
    }

    @Transactional
    public List<AddressDTO> getPartyAddresses(Long partyId,Long partyRoleTypeId){
        return  genPartyAddressRepository.findByGenPartyAddressId_GenPartyRole_PartyIdAndGenPartyAddressId_GenPartyRole_PartyRoleTypeId(partyId,partyRoleTypeId)
                .stream()
                .map(addressDTOMapper).collect(Collectors.toList());
    }

    @Transactional
    public List<ContactPointDTO> getContactPoints(Long partyId, Long partyRoleTypeId){
        return  genPartyContatctPointRepository.findByGenPartyContactPointId_GenPartyRole_PartyIdAndGenPartyContactPointId_GenPartyRole_PartyRoleTypeId(partyId,partyRoleTypeId)
                .stream()
                .map(contactPointDTOMapper).collect(Collectors.toList());
    }

    @Transactional
    public List<PartyIdentityDTO> getPartyIdentities(Long partyId,Long partyRoleTypeId){
        return genPartyIdentityRepository.findByGenPartyIdentityId_GenPartyRole_PartyIdAndGenPartyIdentityId_GenPartyRole_PartyRoleTypeId(partyId,partyRoleTypeId)
                .stream()
                .map(identityDTOMapper).collect(Collectors.toList());


    }
    @Transactional
    public PartyIdentityDTO createPartyIdentity(PartyIdentityDTO partyIdentityDTO){
        GenPartyIdentity genPartyIdentity =identityDTOMapper.mapToPartyIdentity(partyIdentityDTO);
        return identityDTOMapper.apply(genPartyIdentity);
    }



    @Transactional
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Optional<GenPartyAddress> existingAddressOptional = genPartyAddressRepository.findById(
                new GenPartyAddressId(new GenPartyRoleId(addressDTO.getPartyId(), addressDTO.getPartyRoleTypeId()), addressId)
        );

        if (existingAddressOptional.isPresent()) {
            GenPartyAddress existingAddress = existingAddressOptional.get();

            GenPartyAddress updatedAddress = updateAddressDetails(existingAddress, addressDTO);;
            return addressDTOMapper.apply(updatedAddress);
        } else {
            return null;
        }
    }


    @Transactional
    public ContactPointDTO addContactPoint(ContactPointDTO contactPointDTO){
        GenPartyContactPoint genPartyContactPoint=contactPointDTOMapper.mapToContactPoint(contactPointDTO);
        return contactPointDTOMapper.apply(genPartyContactPoint);
    }

    @Transactional
    public ContactPointDTO updateContact(Long contactPointId, ContactPointDTO contactPointDTO) {
        Optional<GenPartyContactPoint> existingContactOptional = genPartyContatctPointRepository.findById(new GenPartyContactPointId(new GenPartyRoleId(contactPointDTO.getPartyId(), contactPointDTO.getPartyRoleTypeId()), contactPointId));

        if (existingContactOptional.isPresent()) {
            GenPartyContactPoint existingContact = existingContactOptional.get();

            GenPartyContactPoint updatedContact = updateContactDetails(existingContact, contactPointDTO);;
            return contactPointDTOMapper.apply(updatedContact);
        } else {
            return null;
        }
    }

    private GenPartyContactPoint updateContactDetails(GenPartyContactPoint existingContact, ContactPointDTO contactPointDTO) {
        // Update address details based on the fields in AddressDTO
        existingContact.getContactPoint().setContactNumber(contactPointDTO.getContactNumber());
        existingContact.getContactPoint().setCountryCode(contactPointDTO.getCountryCode());
        existingContact.getContactPoint().setContactPointTypeCd(contactPointDTO.getContactPointTypeCd());

        // Update other address details as needed


        // Save the updated address
        GenContactPoint savedContact = genContactPointRepository.save(existingContact.getContactPoint());

        // Set the updated address in the party address
        existingContact.setContactPoint(savedContact);

        // Save the changes
        return genPartyContatctPointRepository.save(existingContact);
    }
    private GenPartyAddress updateAddressDetails(GenPartyAddress existingAddress, AddressDTO addressDTO) {
        // Update address details based on the fields in AddressDTO
        existingAddress.getAddress().setAddrName(addressDTO.getAddrName());
        existingAddress.getAddress().setAddrTypeCd(addressDTO.getAddrTypeCd());
        existingAddress.getAddress().setAddrLine1(addressDTO.getAddrLine1());
        existingAddress.getAddress().setAddrLine2(addressDTO.getAddrLine2());
        existingAddress.getAddress().setCountryId(addressDTO.getCountryId());
        existingAddress.getAddress().setProvinceId(addressDTO.getProvinceId());
        existingAddress.getAddress().setCityId(addressDTO.getCityId());

        // Update other address details as needed

        // Fetch the updated country, province, and city details
        Optional<Countries> country = countryRepository.findById(addressDTO.getCountryId());
        Optional<Provinces> province = provinceRepository.findById(addressDTO.getProvinceId());
        Optional<Cities> city = cityRepository.findById(addressDTO.getCityId());

        // Ensure the country, province, and city details exist before updating
        country.ifPresent(existingAddress.getAddress()::setCountry);
        province.ifPresent(existingAddress.getAddress()::setProvinces);
        city.ifPresent(existingAddress.getAddress()::setCity);

        // Save the updated address
        GenAddress savedAddress = genAddressRepository.save(existingAddress.getAddress());

        // Set the updated address in the party address
        existingAddress.setAddress(savedAddress);

        // Save the changes
        return genPartyAddressRepository.save(existingAddress);
    }







}
