package com.heliang.entity;

import java.math.BigDecimal;

public class Product {

    private Long id;

    private String brand;

    private String model;

    private BigDecimal rent;

    private String licence;

    private Boolean status;

    public Product() {

    }

    public Product(Long id, String brand, String model, BigDecimal rent, String licence) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.rent = rent;
        this.licence = licence;
    }

    public Product(String brand, String model, BigDecimal rent, String licence) {
        this.brand = brand;
        this.model = model;
        this.rent = rent;
        this.licence = licence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
