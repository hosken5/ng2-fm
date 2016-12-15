package com.yimei.cron.service;

import com.github.pagehelper.PageHelper;
import com.yimei.cron.domain.Coalbuy;
import com.yimei.cron.mapper.CoalbuyMapper;
import com.yimei.cron.util.Pager;
import com.yimei.cron.web.dto.TaskListParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongpf on 16/12/15.
 */
@Service
public class CoalbuyService {

    Logger logger = LoggerFactory.getLogger(CoalbuyService.class);

    @Autowired
    CoalbuyMapper coalbuyMapper ;

    public Pager<Coalbuy> loadCoalbuy(TaskListParam param ){
        PageHelper.startPage(param.getPage(),param.getPageSize(),true);
        return    Pager.of(coalbuyMapper.loadCoalbuy("")) ;
    }

    public List<Coalbuy> loadCoalbuyList(Integer pageNum , Integer pageSize ){
        PageHelper.startPage(pageNum,pageSize,true);
        return coalbuyMapper.loadTaskLogList() ;
    }

    public int addCoalbuy(Coalbuy coalbuy) {
        return coalbuyMapper.addCoalbuy(coalbuy) ;
    }
}
