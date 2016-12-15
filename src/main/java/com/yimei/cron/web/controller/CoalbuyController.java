package com.yimei.cron.web.controller;

import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Coalbuy;
import com.yimei.cron.domain.Task;
import com.yimei.cron.service.CoalbuyService;
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
 * Created by hongpf on 16/12/15.
 */
@Controller
public class CoalbuyController {

    Logger logger = LoggerFactory.getLogger(CoalbuyController.class);

    @Autowired
    CoalbuyService  coalbuyService;


    @RequestMapping(value = "/coalbuy/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
            @RequestBody Coalbuy coalbuy
    ) {
        logger.info("create coalbuy:"+coalbuy);
        coalbuyService.addCoalbuy(coalbuy);
        return Result.success();
    }

    @RequestMapping(value = "/coalbuy/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete (@RequestBody Task task){
        return null ;
    }

    @RequestMapping(value = "/coalbuy/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
            @RequestBody TaskListParam param
    ){
        Pager<Coalbuy> coalbuyPager =   coalbuyService.loadCoalbuy(param) ;
        return coalbuyPager;
    }
}
