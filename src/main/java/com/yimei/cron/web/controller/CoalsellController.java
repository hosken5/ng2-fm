package com.yimei.cron.web.controller;

import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Coalsell;
import com.yimei.cron.service.CoalsellService;
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
public class CoalsellController {

    Logger logger = LoggerFactory.getLogger(CoalsellController.class);

    @Autowired
    CoalsellService coalsellService;


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
        return  coalsellService.selectByPrimaryKey(coalsellid);
    }

    @RequestMapping(value = "/coalsell/list", method = RequestMethod.POST)
    @ResponseBody
    public Object loadCoalsellbyParam(
            @RequestBody CoalsellParam param
    ){
        logger.info("loadCoalsellbyParam:"+param);
        Pager<Coalsell> coalsellPager = coalsellService.loadCoalsell(param) ;
        return coalsellPager;
    }
}