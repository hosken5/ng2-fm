package com.yimei.cron.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Coalsell {
    private Integer id;

    private Integer teamid;

    private Integer financecellid;

    private String uppercomp;

    private String lowercomp;

    private String ywx;

    private String rzfs;

    private BigDecimal htzjll;

    private BigDecimal zrll;

    private BigDecimal yfkbl;

    private Integer jsl;

    private LocalDate upperjsrq;

    private LocalDate lowerjsrq;

    private String ysfs;

    private LocalDate fyrq;

    private BigDecimal xshsze;

    private BigDecimal cgmze;

    private BigDecimal rmtzsy;

    private BigDecimal upperzjzy;

    private BigDecimal lowerzjzy;

    private LocalDate createtime;

    private LocalDate lastupdatetime;

    private String creator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Integer getFinancecellid() {
        return financecellid;
    }

    public void setFinancecellid(Integer financecellid) {
        this.financecellid = financecellid;
    }

    public String getUppercomp() {
        return uppercomp;
    }

    public void setUppercomp(String uppercomp) {
        this.uppercomp = uppercomp == null ? null : uppercomp.trim();
    }

    public String getLowercomp() {
        return lowercomp;
    }

    public void setLowercomp(String lowercomp) {
        this.lowercomp = lowercomp == null ? null : lowercomp.trim();
    }

    public String getYwx() {
        return ywx;
    }

    public void setYwx(String ywx) {
        this.ywx = ywx == null ? null : ywx.trim();
    }

    public String getRzfs() {
        return rzfs;
    }

    public void setRzfs(String rzfs) {
        this.rzfs = rzfs == null ? null : rzfs.trim();
    }

    public BigDecimal getHtzjll() {
        return htzjll;
    }

    public void setHtzjll(BigDecimal htzjll) {
        this.htzjll = htzjll;
    }

    public BigDecimal getZrll() {
        return zrll;
    }

    public void setZrll(BigDecimal zrll) {
        this.zrll = zrll;
    }

    public BigDecimal getYfkbl() {
        return yfkbl;
    }

    public void setYfkbl(BigDecimal yfkbl) {
        this.yfkbl = yfkbl;
    }

    public Integer getJsl() {
        return jsl;
    }

    public void setJsl(Integer jsl) {
        this.jsl = jsl;
    }

    public LocalDate getUpperjsrq() {
        return upperjsrq;
    }

    public void setUpperjsrq(LocalDate upperjsrq) {
        this.upperjsrq = upperjsrq;
    }

    public LocalDate getLowerjsrq() {
        return lowerjsrq;
    }

    public void setLowerjsrq(LocalDate lowerjsrq) {
        this.lowerjsrq = lowerjsrq;
    }

    public String getYsfs() {
        return ysfs;
    }

    public void setYsfs(String ysfs) {
        this.ysfs = ysfs == null ? null : ysfs.trim();
    }

    public LocalDate getFyrq() {
        return fyrq;
    }

    public void setFyrq(LocalDate fyrq) {
        this.fyrq = fyrq;
    }

    public BigDecimal getXshsze() {
        return xshsze;
    }

    public void setXshsze(BigDecimal xshsze) {
        this.xshsze = xshsze;
    }

    public BigDecimal getCgmze() {
        return cgmze;
    }

    public void setCgmze(BigDecimal cgmze) {
        this.cgmze = cgmze;
    }

    public BigDecimal getRmtzsy() {
        return rmtzsy;
    }

    public void setRmtzsy(BigDecimal rmtzsy) {
        this.rmtzsy = rmtzsy;
    }

    public BigDecimal getUpperzjzy() {
        return upperzjzy;
    }

    public void setUpperzjzy(BigDecimal upperzjzy) {
        this.upperzjzy = upperzjzy;
    }

    public BigDecimal getLowerzjzy() {
        return lowerzjzy;
    }

    public void setLowerzjzy(BigDecimal lowerzjzy) {
        this.lowerzjzy = lowerzjzy;
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
}