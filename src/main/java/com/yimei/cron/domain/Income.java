package com.yimei.cron.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by hongpf on 17/1/6.
 */
public class Income {

    public Income(){}
    public Income(Income i){
        super();
        fkrq = i.getFkrq();
        fkje=i.getFkje();
        fktype=i.getFktype();
        hkrq = i.getHkrq() ;
        hkje = i.getHkje() ;
        txts = i.getTxts() ;
        txx=i.getTxx() ;
        ll=i.getLl() ;
        sybj = i.getSybj()  ;
        htzjll = i.getHtzjll();
    }

    private LocalDate  fkrq ;

    private BigDecimal fkje ;

    private String   fktype ;

    private LocalDate hkrq  ;

    private BigDecimal hkje ;

    private Integer   txts ;

    private BigDecimal txx  ;

    private BigDecimal ll  ;

    private BigDecimal sybj ;

    private  BigDecimal fklx ;
    private BigDecimal htzjll;

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

    public String getFktype() {
        return fktype;
    }

    public void setFktype(String fktype) {
        this.fktype = fktype;
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

    public BigDecimal getLl() {
        return ll;
    }

    public void setLl(BigDecimal ll) {
        this.ll = ll;
    }

    public BigDecimal getSybj() {
        return sybj;
    }

    public void setSybj(BigDecimal sybj) {
        this.sybj = sybj;
    }

    public BigDecimal getFklx() {
        return fklx;
    }

    public void setFklx(BigDecimal fklx) {
        this.fklx = fklx;
    }

    public BigDecimal getHtzjll() {
        return htzjll;
    }

    public void setHtzjll(BigDecimal htzjll) {
        this.htzjll = htzjll;
    }
}
