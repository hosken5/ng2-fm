package com.yimei.cron.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Paymentinfozy {
    private Integer id;

    private Integer coalsellid;

    private LocalDate fkrq;

    private BigDecimal fkje;

    private Integer jxts;

    private BigDecimal ll;

    private BigDecimal rmtsy;

    private String bz;

    private LocalDateTime createtime;

    private LocalDateTime lastupdatetime;

    private String creator;

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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
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
        this.creator = creator == null ? null : creator.trim();
    }
}