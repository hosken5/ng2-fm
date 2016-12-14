package com.yimei.cron.service;

import com.github.pagehelper.PageHelper;
import com.yimei.cron.domain.TaskLog;
import com.yimei.cron.mapper.TaskLogMapper;
import com.yimei.cron.util.Pager;
import com.yimei.cron.web.dto.TaskListParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongpf on 16/10/10.
 */
@Service
public class TaskLogService {

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    TaskLogMapper taskLogMapper ;

    public Pager<TaskLog> loadTaskLog(TaskListParam param ){
        PageHelper.startPage(param.getPage(),param.getPageSize(),true);
        return    Pager.of(taskLogMapper.loadTaskLog(param)) ;
    }

    public List<TaskLog> loadTaskLogList(Integer pageNum , Integer pageSize ){
        PageHelper.startPage(pageNum,pageSize,true);
        return taskLogMapper.loadTaskLogList() ;
    }

    public int addTaskLogStart(TaskLog taskLog) {
       return taskLogMapper.addTaskLog(taskLog) ;
    }

    public int addTaskLogEnd(TaskLog taskLog) {
        return taskLogMapper.addTaskLogEnd(taskLog) ;
    }
}
