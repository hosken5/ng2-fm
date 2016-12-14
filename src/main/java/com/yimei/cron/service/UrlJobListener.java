package com.yimei.cron.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hongpf on 16/10/10.
 */
@Service
public class UrlJobListener  implements JobListener {

    @Autowired
    TaskLogService taskLogService ;

    @Override
    public String getName() {
        return "UrlJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
//
//        Task task =  (Task) context.getJobDetail().getJobDataMap().get("task") ;
//        TaskLog  taskLog=  new TaskLog() ;
//        taskLog.setTaskid(task.getId());
//        taskLog.setMessage("start");
//        taskLog.setTaskname(task.getTaskname());
//        taskLog.setStarttime(context.getFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//        taskLogService.addTaskLogStart(taskLog);
//        System.out.println("jobToBeExecuted"+context.getJobDetail());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
//        System.out.println("jobExecutionVetoed"+context.getJobDetail());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

//        Task task =  (Task) context.getJobDetail().getJobDataMap().get("task") ;
//
//        TaskLog  taskLog=  new TaskLog() ;
//        taskLog.setTaskid(task.getId());
//        taskLog.setMessage("end");
//        taskLog.setTaskname(task.getTaskname());
//        taskLog.setEndtime(LocalDateTime.now());
////        taskLog.setEndtime();
////        taskLog.setStarttime(context.getFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//        taskLogService.addTaskLogStart(taskLog);
//        System.out.println("jobWasExecuted"+context.getJobDetail());
    }
}
