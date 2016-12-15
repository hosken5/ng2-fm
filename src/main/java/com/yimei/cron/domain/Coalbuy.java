package com.yimei.cron.domain;

/**
 * Created by hongpf on 16/12/15.
 */

/**
 *
 *`ywtd` varchar(50) DEFAULT NULL COMMENT '业务团队',
 `fydate` int(11) DEFAULT NULL  COMMENT '发运日期',
 `ywlx` varchar(8) DEFAULT NULL  COMMENT  '业务类型',
 `rzfs` varchar(8) DEFAULT NULL COMMENT '融资方式',
 `rzll` FLOAT DEFAULT NULL  COMMENT '融资利率',
 `yfkbl` INTEGER DEFAULT  NULL  COMMENT '预付款比例',
 `ysfs` VARCHAR(8) DEFAULT  null COMMENT  '运输方式',
 `mz` VARCHAR(2) DEFAULT NULL  COMMENT '煤种',
 `ch`VARCHAR(2) DEFAULT  NULL  COMMENT  '船号',
 customerid  INT(11) unsigned NOT NULL COMMENT '客户id',
 cgdw DECIMAL(20,2)  null DEFAULT  0 COMMENT '采购吨位',
 cgmze  DECIMAL(20,2) null  DEFAULT  0  COMMENT '采购煤款总额',
 `createtime` timestamp NULL DEFAULT NULL  COMMENT '',
 `lastupdatetime` timestamp NULL DEFAULT NULL,
 `valid` int(11) DEFAULT NULL,
 `validtime` timestamp NULL DEFAULT NULL,
 `lastaccessstarttime` timestamp NULL DEFAULT NULL,
 `lastaccessstatus` varchar(11) DEFAULT NULL,
 `lastaccessmsg` varchar(200) DEFAULT NULL,
 `lastaccessendtime` timestamp NULL DEFAULT NULL,
 *
 *
 *
 */

public class Coalbuy {
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
