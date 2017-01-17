package com.yimei.cron.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Paymentinfo {
    private Integer id;

    private Integer coalsellid;

    private LocalDate fkrq;

    private BigDecimal fkje;

    private Integer jxts;

    private BigDecimal ll;

    private BigDecimal rmtsy;

    private LocalDate hkrq;

    private BigDecimal hkje;

    private Integer hkjxts;

    private BigDecimal hkll;

    private BigDecimal hkrmtsy;

    private LocalDate createtime;

    private LocalDate lastupdatetime;

    private String creator;

    private String bz;

    private BigDecimal cfsy ;
    private  Integer jklx ;

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

    public LocalDate getFkrq() {
        return fkrq;
    }

    public void setFkrq(LocalDate fkrq) {
        this.fkrq = fkrq;
    }

    public BigDecimal getFkje() {
        return fkje;
    }

    public void setFkje(BigDecimal fkje) {
        this.fkje = fkje;
    }

    public Integer getJxts() {
        return jxts;
    }

    public void setJxts(Integer jxts) {
        this.jxts = jxts;
    }

    public BigDecimal getLl() {
        return ll;
    }

    public void setLl(BigDecimal ll) {
        this.ll = ll;
    }

    public BigDecimal getRmtsy() {
        return rmtsy;
    }

    public void setRmtsy(BigDecimal rmtsy) {
        this.rmtsy = rmtsy;
    }

    public LocalDate getHkrq() {
        return hkrq;
    }

    public void setHkrq(LocalDate hkrq) {
        this.hkrq = hkrq;
    }

    public BigDecimal getHkje() {
        return hkje;
    }

    public void setHkje(BigDecimal hkje) {
        this.hkje = hkje;
    }

    public Integer getHkjxts() {
        return hkjxts;
    }

    public void setHkjxts(Integer hkjxts) {
        this.hkjxts = hkjxts;
    }

    public BigDecimal getHkll() {
        return hkll;
    }

    public void setHkll(BigDecimal hkll) {
        this.hkll = hkll;
    }

    public BigDecimal getHkrmtsy() {
        return hkrmtsy;
    }

    public void setHkrmtsy(BigDecimal hkrmtsy) {
        this.hkrmtsy = hkrmtsy;
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

    public BigDecimal getCfsy() {
        return cfsy;
    }

    public void setCfsy(BigDecimal cfsy) {
        this.cfsy = cfsy;
    }

    public Integer getJklx() {
        return jklx;
    }

    public void setJklx(Integer jklx) {
        this.jklx = jklx;
    }
}