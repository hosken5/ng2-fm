package com.yimei.cron.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hongpf on 16/9/28.
 */

public class Task  implements Serializable{
    private Long    id ;
    private String  taskname ;
    private String  description ;
    private String  cron ;
    private String  resource ;
    private String  timeunit ;
    private Integer time ;
    private Integer startHour ;
    private Integer startMin ;
    private Integer startSec ;
    private boolean off ;


    private Integer triggertype ;

    private int status ;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime ;
    private String creator ;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastaccessstarttime ;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastaccessendtime ;
    private String lastaccessmsg ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }


    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public LocalDateTime getLastaccessstarttime() {
        return lastaccessstarttime;
    }

    public void setLastaccessstarttime(LocalDateTime lastaccessstarttime) {
        this.lastaccessstarttime = lastaccessstarttime;
    }

    public LocalDateTime getLastaccessendtime() {
        return lastaccessendtime;
    }

    public void setLastaccessendtime(LocalDateTime lastaccessendtime) {
        this.lastaccessendtime = lastaccessendtime;
    }

    public String getLastaccessmsg() {
        return lastaccessmsg;
    }

    public void setLastaccessmsg(String lastaccessmsg) {
        this.lastaccessmsg = lastaccessmsg;
    }

    public String getTimeunit() {
        return timeunit;
    }

    public void setTimeunit(String timeunit) {
        this.timeunit = timeunit;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getStartMin() {
        return startMin;
    }

    public void setStartMin(Integer startMin) {
        this.startMin = startMin;
    }

    public Integer getStartSec() {
        return startSec;
    }

    public void setStartSec(Integer startSec) {
        this.startSec = startSec;
    }

    public Integer getTriggertype() {
        return triggertype;
    }

    public void setTriggertype(Integer triggertype) {
        this.triggertype = triggertype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStarted(boolean t ){
        this.status = t? 1 : 0  ;
    }
    public boolean isRunning(){
        return  this.status == 1 ;
    }

    public boolean isOff() {
        return off;
    }

    public void setOff(boolean off) {
        this.off = off;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskname='" + taskname + '\'' +
                ", description='" + description + '\'' +
                ", cron='" + cron + '\'' +
                ", resource='" + resource + '\'' +
                ", timeunit='" + timeunit + '\'' +
                ", time=" + time +
                ", startHour=" + startHour +
                ", startMin=" + startMin +
                ", startSec=" + startSec +
                ", triggertype=" + triggertype +
                ", createtime=" + createtime +
                ", creator='" + creator + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", lastaccessstarttime=" + lastaccessstarttime +
                ", lastaccessendtime=" + lastaccessendtime +
                ", lastaccessmsg='" + lastaccessmsg + '\'' +
                '}';
    }
}