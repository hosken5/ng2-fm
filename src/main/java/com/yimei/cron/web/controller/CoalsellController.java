package com.yimei.cron.web.controller;

import com.yimei.cron.basic.annotation.LoginRequired;
import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Coalsell;
import com.yimei.cron.service.CoalsellService;
import com.yimei.cron.service.Session;
import com.yimei.cron.util.Pager;
import com.yimei.cron.web.dto.CoalsellParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hongpf on 16/12/21.
 */
@Controller
@LoginRequired
public class CoalsellController {

    Logger logger = LoggerFactory.getLogger(CoalsellController.class);

    @Autowired
    CoalsellService coalsellService;

    @Autowired
    Session session  ;

    /**
     * add or update
     * @param coalsell
     * @return
     */

    @RequestMapping(value = "/coalsell/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
            @RequestBody Coalsell coalsell
    ){
        if(coalsell.getId()==null){
            coalsell.setCreator(session.getUser().getId().toString());
            logger.info("create coalsell",coalsell);
            coalsellService.insertSelective(coalsell);
        }else {
            coalsellService.updateByPrimaryKeySelective(coalsell);
        }
        return Result.success();
    }
    @RequestMapping(value = "/coalsell/query/{coalsellid}", method = RequestMethod.POST)
    @ResponseBody
    public Object select(
            @PathVariable("coalsellid") Integer coalsellid
    ){
        logger.info("selectByPrimaryKey:"+coalsellid);
        Coalsell  coalsell = coalsellService.selectByPrimaryKey(coalsellid);
        if( (!coalsell.getCreator().equals(session.getUser().getId().toString())) && session.getUser().getRole().equals("2")){
            coalsell = null ;
        }
        return  coalsell ;
    }

    @RequestMapping(value = "/coalsell/list", method = RequestMethod.POST)
    @ResponseBody
    public Object loadCoalsellbyParam(
            @RequestBody CoalsellParam param
    ){
        param.setUserid(session.getUser().getId());
        logger.info("loadCoalsellbyParam:"+param);

        Pager<Coalsell> coalsellPager = coalsellService.loadCoalsell(param) ;
        return coalsellPager;
    }
}