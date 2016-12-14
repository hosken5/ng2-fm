package com.yimei.cron.domain;

import java.time.LocalDateTime;

/**
 * Created by hongpf on 16/10/10.
 */
public class TaskLog {

    private Long id ;
    private Long taskid  ;
    private String taskname ;
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private String message ;

    private Boolean reqsuccess ;

    private Boolean ressuccess ;

    private String  resmessage ;

    private LocalDateTime restime ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public LocalDateTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getReqsuccess() {
        return reqsuccess;
    }

    public void setReqsuccess(Boolean reqsuccess) {
        this.reqsuccess = reqsuccess;
    }

    public Boolean getRessuccess() {
        return ressuccess;
    }

    public void setRessuccess(Boolean ressuccess) {
        this.ressuccess = ressuccess;
    }

    public String getResmessage() {
        return resmessage;
    }

    public void setResmessage(String resmessage) {
        this.resmessage = resmessage;
    }

    public LocalDateTime getRestime() {
        return restime;
    }

    public void setRestime(LocalDateTime restime) {
        this.restime = restime;
    }
}
