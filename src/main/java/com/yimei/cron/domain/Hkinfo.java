package com.yimei.cron.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Hkinfo {

    private Integer id;

    private Integer coalsellid;

    private LocalDate hkrq;

    private BigDecimal hkje;

    private String hkfs;

    private BigDecimal ll;

    private LocalDate dqr;

    private Integer txts;

    private BigDecimal txx;

    private String bz;

    private LocalDate createtime;

    private LocalDate lastupdatetime;

    private String creator;

    private BigDecimal pmje ;

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

    public String getHkfs() {
        return hkfs;
    }

    public void setHkfs(String hkfs) {
        this.hkfs = hkfs == null ? null : hkfs.trim();
    }

    public BigDecimal getLl() {
        return ll;
    }

    public void setLl(BigDecimal ll) {
        this.ll = ll;
    }

    public LocalDate getDqr() {
        return dqr;
    }

    public void setDqr(LocalDate dqr) {
        this.dqr = dqr;
    }

    public Integer getTxts() {
        return txts;
    }

    public void setTxts(Integer txts) {
        this.txts = txts;
    }

    public BigDecimal getTxx() {
        return txx;
    }

    public void setTxx(BigDecimal txx) {
        this.txx = txx;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
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

    public BigDecimal getPmje() {
        return pmje;
    }

    public void setPmje(BigDecimal pmje) {
        this.pmje = pmje;
    }
}