package com.heliang.dto;

public class StatisticInfo {

    private Integer totalProduct;

    private Integer availableProduct;

    private Integer totalUser;

    private Integer totalBusiness;

    public StatisticInfo(Integer totalProduct, Integer availableProduct, Integer totalUser, Integer totalBusiness) {
        this.totalProduct = totalProduct;
        this.availableProduct = availableProduct;
        this.totalUser = totalUser;
        this.totalBusiness = totalBusiness;
    }

    public Integer getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(Integer totalProduct) {
        this.totalProduct = totalProduct;
    }

    public Integer getAvailableProduct() {
        return availableProduct;
    }

    public void setAvailableProduct(Integer availableProduct) {
        this.availableProduct = availableProduct;
    }

    public Integer getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    public Integer getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(Integer totalBusiness) {
        this.totalBusiness = totalBusiness;
    }
}
