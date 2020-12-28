package com.heliang.dto;

import com.heliang.entity.Product;

import java.math.BigDecimal;
import java.util.Date;

public class LeaseDto {

    private Long id;

    private Long userId;

    private Product product;

    private Integer duration;

    private BigDecimal cost;

    private Date expireTime;

    private Boolean expired;

    private Boolean backed;

    public LeaseDto() {

    }

    public LeaseDto(Long id, Long userId, Product product, Integer duration, BigDecimal cost, Date expireTime, Boolean expired, Boolean backed) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.duration = duration;
        this.cost = cost;
        this.expireTime = expireTime;
        this.expired = expired;
        this.backed = backed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getBacked() {
        return backed;
    }

    public void setBacked(Boolean backed) {
        this.backed = backed;
    }
}
