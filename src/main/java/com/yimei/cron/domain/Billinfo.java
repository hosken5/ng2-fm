package com.yimei.cron.domain;

import java.time.LocalDate;

public class Billinfo {
    private Integer id;

    private Integer coalsellid;

    private Integer amount;

    private Integer quantity;

    private LocalDate createtime;

    private LocalDate lastupdatetime;

    private String creator;

    private String bz;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public LocalDate getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(LocalDate lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
}