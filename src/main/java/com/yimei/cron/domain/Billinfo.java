package com.yimei.cron.domain;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Billinfo {
    private Integer id;

    private Integer coalsellid;

    private BigDecimal amount;

    private Integer quantity;

    private LocalDateTime createtime;

    private LocalDateTime lastupdatetime;

    private String creator;

    private String bz;

    private LocalDate kpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoalsellid() {
        return coalsellid;
    }

    public void setCoalsellid(Integer coalsellid) {
        this.coalsellid = coalsellid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(LocalDateTime lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public LocalDate getKpdate() {
        return kpdate;
    }

    public void setKpdate(LocalDate kpdate) {
        this.kpdate = kpdate;
    }
}