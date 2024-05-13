package com.example.demo.CommonFramework.Model.Address;

import jakarta.persistence.*;

@Entity
@Table(name="GEN_PROVINCES")
public class Provinces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVINCE_ID")
    private Long provinceId;

    @Column(name = "PROVINCE_NAME", length = 20, nullable = false)
    private String provinceName;

    @Column(name = "COUNTRY_ID")  // Add this line
    private Long countryId;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID",insertable = false, updatable = false)
    private Countries countries;

    public Provinces() {
    }

    public Long getId() {
        return provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
