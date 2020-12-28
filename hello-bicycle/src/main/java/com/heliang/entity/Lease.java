package com.heliang.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Lease {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer duration;

    private BigDecimal cost;

    private Date expireTime;

    private Boolean expired;

    private Boolean backed;

    public Lease() {

    }

    public Lease(Long userId, Long productId, Integer duration, BigDecimal cost, Date expireTime) {
        this.userId = userId;
        this.productId = productId;
        this.duration = duration;
        this.cost = cost;
        this.expireTime = expireTime;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
