package com.yimei.cron.service;

import com.yimei.cron.domain.Task;
import com.yimei.cron.domain.TaskLog;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by hongpf on 16/10/10.
 */
@Service
public class UrlJobRunner implements Job, ApplicationContextAware {



    private static TaskLogService  taskLogService    ;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException  {

        TaskLog tasklogStart = logStart(context) ;

        RestTemplate template  = new RestTemplate();
        Task task =  (Task) context.getJobDetail().getJobDataMap().get("task") ;
        String url  = task.getResource() ;
        try {
            String result = template.getForObject(url, String.class);
        }catch (Exception e ){
            TaskLog taskLog=  new TaskLog() ;
            taskLog.setId(tasklogStart.getId());
            taskLog.setTaskid(task.getId());
            taskLog.setMessage("error");
            taskLog.setTaskname(task.getTaskname());
            taskLog.setEndtime(LocalDateTime.now());
            taskLog.setMessage(ExceptionUtils.getStackTrace(e));
            taskLog.setReqsuccess(false);
            taskLogService.addTaskLogEnd(taskLog) ;
        }

    }

    private TaskLog logStart(JobExecutionContext context) {
        Task task =  (Task) context.getJobDetail().getJobDataMap().get("task") ;
        TaskLog  taskLog=  new TaskLog() ;
        taskLog.setTaskid(task.getId());
        taskLog.setTaskname(task.getTaskname());
        taskLog.setStarttime(context.getFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
//        taskLog.setReqsuccess();
        taskLogService.addTaskLogStart(taskLog);
        return  taskLog  ;
    }


    @Override
    public  void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        taskLogService = applicationContext.getBean(TaskLogService.class);
    }
}
