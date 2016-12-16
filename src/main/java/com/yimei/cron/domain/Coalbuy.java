package com.yimei.cron.domain;

/**
 * Created by hongpf on 16/12/15.
 */

import java.io.Serializable;

/**
 *
 *
 */

public class Coalbuy implements Serializable {
    private Long    id ;
    private Long  teamid ;
    private String fydate  ;
    private String rzfs  ;
    private String rzll  ;
    private String yfkbl  ;
    private String ysfs  ;
    private String mz  ;
    private String ch  ;
    private Long  customerid  ;
    private String cgdw  ;
    private String cgmze  ;
    private String createtime  ;
    private String lastupdatetime  ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamid() {
        return teamid;
    }

    public void setTeamid(Long teamid) {
        this.teamid = teamid;
    }

    public String getFydate() {
        return fydate;
    }

    public void setFydate(String fydate) {
        this.fydate = fydate;
    }

    public String getRzfs() {
        return rzfs;
    }

    public void setRzfs(String rzfs) {
        this.rzfs = rzfs;
    }

    public String getRzll() {
        return rzll;
    }

    public void setRzll(String rzll) {
        this.rzll = rzll;
    }

    public String getYfkbl() {
        return yfkbl;
    }

    public void setYfkbl(String yfkbl) {
        this.yfkbl = yfkbl;
    }

    public String getYsfs() {
        return ysfs;
    }

    public void setYsfs(String ysfs) {
        this.ysfs = ysfs;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getCgdw() {
        return cgdw;
    }

    public void setCgdw(String cgdw) {
        this.cgdw = cgdw;
    }

    public String getCgmze() {
        return cgmze;
    }

    public void setCgmze(String cgmze) {
        this.cgmze = cgmze;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}
