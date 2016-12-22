package com.yimei.cron.web.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by hongpf on 16/12/21.
 */
public class CoalsellParam   extends PageParam implements Serializable {
    private Integer teamid  ;
    private Integer financecellid ;
    private LocalDate sfydate;
    private LocalDate efydate;
    private String uppercomp;
    private String lowercomp;

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

    public LocalDate getSfydate() {
        return sfydate;
    }

    public void setSfydate(LocalDate sfydate) {
        this.sfydate = sfydate;
    }

    public LocalDate getEfydate() {
        return efydate;
    }

    public void setEfydate(LocalDate efydate) {
        this.efydate = efydate;
    }

    public String getUppercomp() {
        return uppercomp;
    }

    public void setUppercomp(String uppercomp) {
        this.uppercomp = uppercomp;
    }

    public String getLowercomp() {
        return lowercomp;
    }

    public void setLowercomp(String lowercomp) {
        this.lowercomp = lowercomp;
    }

    @Override
    public String toString() {
        return "CoalsellParam{" +
                "teamid=" + teamid +
                ", financecellid=" + financecellid +
                ", sfydate=" + sfydate +
                ", efydate=" + efydate +
                ", uppercomp='" + uppercomp + '\'' +
                ", lowercomp='" + lowercomp + '\'' +
                '}';
    }
}
