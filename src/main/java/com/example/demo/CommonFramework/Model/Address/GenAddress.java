package com.example.demo.CommonFramework.Model.Address;

import com.example.demo.CommonFramework.Model.Facility.GenFacilityAddress;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GEN_ADDRESS")
public class GenAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Column(name = "ADDR_NAME", length = 20, nullable = true)
    private String addrName;

    @Column(name = "ADDR_TYPE_CD", nullable = true)
    private int addrTypeCd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID", insertable=false, updatable=false)
    private Countries country;

    @Column(name = "COUNTRY_ID")  // Add this line
    private Long countryId;

//    @Column(name = "STATE_ID")
//    private Long stateId;

//    @Column(name = "COUNTRY_NAME", length = 20, nullable = true)
//    private String countryName;

//    @Column(name = "PROVINCE_NAME", length = 20, nullable = false)
//    private String provinceName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVINCE_ID", insertable=false, updatable=false)
    private Provinces provinces;

    @Column(name = "PROVINCE_ID")  // Add this line
    private Long provinceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID", insertable=false, updatable=false)
    private Cities city;

    @Column(name = "CITY_ID")  // Add this line
    private Long cityId;

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Column(name = "ADDR_LINE1", length = 40, nullable = false)
    private String addrLine1;

    @Column(name = "ADDR_LINE2", length = 40, nullable = true)
    private String addrLine2;

    @OneToMany(mappedBy = "address")
    private List<GenPartyAddress> genPartyAddresses=new ArrayList<>();

    @OneToMany(mappedBy = "genAddress", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GenFacilityAddress> facilityAddresses=new ArrayList<>();

    public void addFacilityAddress(GenFacilityAddress genFacilityAddress){
        facilityAddresses.add(genFacilityAddress);
        genFacilityAddress.setGenAddress(this);
    }


    public void addGenPartyAddress(GenPartyAddress genPartyAddress){
        genPartyAddresses.add(genPartyAddress);
        genPartyAddress.setAddress(this);
    }


    public GenAddress(String addrName, int addrTypeCd,
                      Long countryId, Long provinceId,
                      Long cityId, String addrLine1,
                      String addrLine2) {
        this.addrName = addrName;
        this.addrTypeCd = addrTypeCd;
        this.countryId = countryId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
    }

    public GenAddress(){

    }

    public GenAddress(Long addressId, int addrTypeCd, String addrLine1) {
        this.addressId = addressId;
        this.addrTypeCd = addrTypeCd;
        this.addrLine1 = addrLine1;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public Countries getCountry() {
        return country;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public Provinces getProvinces() {
        return provinces;
    }

    public void setProvinces(Provinces provinces) {
        this.provinces = provinces;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
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

    public List<GenPartyAddress> getGenPartyAddress() {
        return genPartyAddresses;
    }

    public List<GenFacilityAddress> getFacilityAddresses() {
        return facilityAddresses;
    }

    public void setFacilityAddresses(List<GenFacilityAddress> facilityAddresses) {
        this.facilityAddresses = facilityAddresses;
    }

    public void setGenPartyAddress(List<GenPartyAddress> genPartyAddresses) {
        this.genPartyAddresses = genPartyAddresses;
    }
}
