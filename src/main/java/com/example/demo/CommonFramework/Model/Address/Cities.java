package com.example.demo.CommonFramework.Model.Address;

import jakarta.persistence.*;

@Entity
@Table(name = "GEN_CITY")
public class Cities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_ID")
    private Long cityId;

    @Column(name = "CITY_NAME", length = 40, nullable = false)
    private String cityName;

    @Column(name = "PROVINCE_ID")  // Add this line
    private Long provinceId;
    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID",insertable = false, updatable = false)
    private Provinces provinces;

    public Long getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public Provinces getProvinces() {
        return provinces;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
