package com.example.demo.CommonFramework.DTO;

import java.util.Objects;

public class ContactDTO {

    private String countryCode;
    private String contactNumber;
    private int contactPointTypeCd;

    // Other attributes, getters, setters, etc.

    // Constructor
    public ContactDTO(String countryCode, String contactNumber, int contactPointTypeCd) {
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
        this.contactPointTypeCd = contactPointTypeCd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDTO that = (ContactDTO) o;
        return contactPointTypeCd == that.contactPointTypeCd && Objects.equals(countryCode, that.countryCode) && Objects.equals(contactNumber, that.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, contactNumber, contactPointTypeCd);
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getContactPointTypeCd() {
        return contactPointTypeCd;
    }

    public void setContactPointTypeCd(int contactPointTypeCd) {
        this.contactPointTypeCd = contactPointTypeCd;
    }
}

