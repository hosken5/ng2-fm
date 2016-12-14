package com.yimei.cron.service;


import com.github.pagehelper.PageHelper;
import com.yimei.cron.domain.Task;
import com.yimei.cron.mapper.TaskMapper;
import com.yimei.cron.util.Pager;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by hongpf on 16/9/28.
 */
@Service
public class TaskService {


    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    Scheduler  scheduler ;

    @Autowired
    TaskMapper taskMapper ;

    public TreeMap<Long , Task> tasks  =  new TreeMap<>((a,b)->a>b?-1:a<b?1:0);

    public void runAllTask() {
        List<Task> taskls =  loadTask();
        taskls.forEach((task)->{
            if(!this.tasks.containsKey(task.getId())){
                this.tasks.put(task.getId(),task);
                if(!task.isOff()){
                    startTask(task);
                }
            }
        });
    }

    List<Task> loadTask(){
        return  taskMapper.loadTask();
    }

    public Pager<Task> loadTask(Integer pageNum ,Integer pageSize ){

        pageNum = pageNum==0?1:pageNum ;
        Pager pager = new  Pager<Task>();
        pager.setPageSize(pageSize);
        pager.setPage(pageNum);
        pager.setTotal(this.tasks.size());

        pager.setData( new ArrayList<>(this.tasks.values())
             .subList((pageSize*(pageNum-1)), Math.min((this.tasks.size()),(pageSize*(pageNum-1)+pageSize))));
        return pager ;

    }

    public List<Task> loadTaskList(Integer pageNum , Integer pageSize ){
        PageHelper.startPage(pageNum,pageSize,true);
        return taskMapper.loadTaskList() ;
    }

    public void  createTask(Task task) {
        task.setOff(false);
        task.setCreator("");
        task.setCreatetime(LocalDateTime.now());
        taskMapper.addTask(task) ;
        runAllTask();
    }

    public Task stopTask(Long id ) {
        Task task =  this.tasks.get(id) ;
        TriggerKey triggerKey =  TriggerKey.triggerKey(getkey(task))  ;
        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(getkey(task)));
        } catch (SchedulerException e) {
           throw new RuntimeException("SchedulerException",e);
        }
        task.setStarted(false);
        task.setOff(true);
        taskMapper.stopTask(task);
        return  task ;
    }

     static String getkey(Task  task ){
        return task.getId()+"_"+task.getTaskname() ;
    }

    public Task deleteTask(Long id){
        Task task =  this.tasks.get(id) ;
        if(task!=null){
            TriggerKey triggerKey =  TriggerKey.triggerKey(getkey(task));
            try {
                logger.info("checkexists"+scheduler.checkExists(JobKey.jobKey(getkey(task))));
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                scheduler.deleteJob(JobKey.jobKey(getkey(task)));
            } catch (SchedulerException e) {
                throw new  RuntimeException("SchedulerException", e ) ;
            }
            task.setStarted(false);
            this.tasks.remove(task.getId());
            this.taskMapper.deleteTask(task);
        }
        return  task ;
    }

    private Trigger getTrigger(Task task ){
        Trigger trigger  ;
        ScheduleBuilder scheduleBuilder ;
        if(task.getTriggertype()==1){ //cron
            scheduleBuilder =  CronScheduleBuilder.cronSchedule(task.getCron());
        }else{ //自定义 scheduler

            DateBuilder.IntervalUnit intervalUnit  = null ;//= DateBuilder.IntervalUnit.DAY  ;
            if(task.getTimeunit().equals("day")){
                intervalUnit = DateBuilder.IntervalUnit.DAY ;
            }else  if (task.getTimeunit().equals("our")){
                intervalUnit = DateBuilder.IntervalUnit.HOUR ;
            }else  if (task.getTimeunit().equals("min")){
                intervalUnit = DateBuilder.IntervalUnit.MINUTE ;
            }else  if (task.getTimeunit().equals("sec")){
                intervalUnit = DateBuilder.IntervalUnit.SECOND ;
            }
            TimeOfDay timeOfDay = new TimeOfDay(task.getStartHour(),task.getStartMin(),task.getStartSec());

            scheduleBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .withInterval(task.getTime(),intervalUnit)
                .startingDailyAt(timeOfDay);
        }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(getkey(task))
                .withSchedule(scheduleBuilder)
                .build();
    }

    private Task startTask(Task task){

        try {
            if(!task.isRunning()&&!scheduler.checkExists(TriggerKey.triggerKey(getkey(task)))){
                JobDataMap jobDataMap =  new JobDataMap () ;
                jobDataMap.put("task",task);
                JobDetail  jobDetail = JobBuilder
                        .newJob(UrlJobRunner.class)
                        .setJobData(jobDataMap)
                        .withIdentity(getkey(task)).build() ;
                Trigger trigger = getTrigger(task) ;
                scheduler.scheduleJob(jobDetail, trigger);
                task.setStarted(true);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException("SchedulerException", e ) ;
        }
        return task;
    }

    public  Task  startTask(Long id ){
        Task task = this.tasks.get(id);
        if(task!=null){
          startTask(task);
          taskMapper.startTask(task);
        }
        return task ;
    }
}