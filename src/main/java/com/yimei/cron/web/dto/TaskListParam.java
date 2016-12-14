package com.yimei.cron.web.dto;

/**
 * Created by hongpf on 16/9/29.
 */
public class TaskListParam  extends PageParam{
    private Long taskid ;

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }
}