package com.example.demo.CommonFramework.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDTO {
    @JsonProperty("partyId")
    private Long partyId;

    @JsonProperty("partyRoleTypeId")
    private Long partyRoleTypeId;

    @JsonIgnore
    private Long addressId;

    @JsonProperty("addrName")
    private String addrName;

    @JsonProperty("addrTypeCd")
    private int addrTypeCd;

    @JsonProperty("countryId")
    private Long countryId;


    @JsonIgnore
    private String countryName;

    @JsonProperty("provinceId")
    private Long provinceId;


    @JsonIgnore
    private String provinceName;

    @JsonProperty("cityId")
    private Long cityId;


    @JsonIgnore
    private String cityName;

    @JsonProperty("addrLine1")
    private String addrLine1;

    @JsonProperty("addrLine2")
    private String addrLine2;

    @JsonProperty("addressId")
    public Long getAddressId() {
        return addressId;
    }

    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("provinceName")
    public String getProvinceName() {
        return provinceName;
    }
    @JsonProperty("cityName")
    public String getCityName() {
        return cityName;
    }

    // Constructors, getters, setters


    public AddressDTO() {
        // Default constructor
    }

    public AddressDTO(Long partyId, Long partyRoleTypeId, Long addressId, String addrName, int addrTypeCd, Long countryId, String countryName, Long provinceId, String provinceName, Long cityId, String cityName, String addrLine1, String addrLine2) {
        this.partyId = partyId;
        this.partyRoleTypeId = partyRoleTypeId;
        this.addressId = addressId;
        this.addrName = addrName;
        this.addrTypeCd = addrTypeCd;
        this.countryId = countryId;
        this.countryName=countryName;
        this.provinceId = provinceId;
        this.provinceName=provinceName;
        this.cityId = cityId;
        this.cityName=cityName;
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
    }

    public AddressDTO(Long addressId, int addrTypeCd, String addrLine1) {
        this.addressId = addressId;
        this.addrTypeCd = addrTypeCd;
        this.addrLine1 = addrLine1;
    }



    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public Long getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(Long partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public int getAddrTypeCd() {
        return addrTypeCd;
    }

    public void setAddrTypeCd(int addrTypeCd) {
        this.addrTypeCd = addrTypeCd;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    public String getAddrLine2() {
        return addrLine2;
    }

    public void setAddrLine2(String addrLine2) {
        this.addrLine2 = addrLine2;
    }

    // Add other methods as needed
}
