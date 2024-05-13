package com.example.demo.CommonFramework.Model.Address;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COUNTRY")
public class Countries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @Column(name = "SHORT_NAME", length = 40)
    private String shortName;

    @Column(name = "LONG_NAME", length = 200, nullable = false)
    private String longName;

    @Column(name = "ISO_CODE2", length = 2)
    private String isoCode2;

    @Column(name = "ISO_CODE3", length = 3)
    private String isoCode3;

    @Column(name = "ISO_NUMBERIC_CODE")
    private int isoNumericCode;

    @Column(name = "PRIMARY_CURRENCY", length = 10)
    private String primaryCurrency;

    @Column(name = "PRIMARY_LANGUAGE", length = 10)
    private String primaryLanguage;

    @OneToMany(mappedBy = "country")
    private List<GenAddress> genAddressList=new ArrayList<>();


    public Countries() {
    }



    public Long getCountryId() {
        return countryId;
    }

    public String getLongName() {
        return longName;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
