package com.yimei.cron.web.controller;

import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Task;
import com.yimei.cron.domain.TaskLog;
import com.yimei.cron.service.TaskLogService;
import com.yimei.cron.service.TaskService;
import com.yimei.cron.util.Pager;
import com.yimei.cron.web.dto.TaskListParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongpf on 16/7/21.
 */
@Controller
public class TaskController {

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskService taskService ;

    @Autowired
    TaskLogService  taskLogService ;


    @RequestMapping(value = "/tasklog/list", method = RequestMethod.POST)
    @ResponseBody
    public Object taskloglist(
            @RequestBody TaskListParam param
    ) {
        Pager<TaskLog> taskLogs = taskLogService.loadTaskLog(param);
        return taskLogs;
    }
    @RequestMapping(value = "/task/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
            @RequestBody TaskListParam param
    ) {
        Pager<Task> tasks = taskService.loadTask(param.getPage(), param.getPageSize());
        return tasks;
    }

    @RequestMapping(value = "/task/list/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
            @RequestBody Task task
    ) {
        logger.info("create task:"+task);
        taskService.createTask(task);
        return Result.success();
    }

    @RequestMapping(value = "/task/list/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete (@RequestBody Task task){
        return taskService.deleteTask(task.getId()) ;

    }

   @RequestMapping(value = "/task/list/stop", method = RequestMethod.POST)
    @ResponseBody
    public Object stop (@RequestBody Task task)  {
        return  this.taskService.stopTask(task.getId());
    }

   @RequestMapping(value = "/task/list/start", method = RequestMethod.POST)
    @ResponseBody
    public Object start (@RequestBody Task task){
       return  this.taskService.startTask(task.getId());
   }

}