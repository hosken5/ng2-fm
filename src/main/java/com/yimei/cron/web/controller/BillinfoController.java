package com.yimei.cron.web.controller;

import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Billinfo;
import com.yimei.cron.service.BillinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hongpf on 16/12/21.
 */
@Controller
public class BillinfoController {

    Logger logger = LoggerFactory.getLogger(BillinfoController.class);

    @Autowired
    BillinfoService billinfoService;

    /**
     * add or update
     * @param billinfo
     * @return
     */

    @RequestMapping(value = "/billinfo/addorupdate", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
            @RequestBody Billinfo billinfo
    ){
        if(billinfo.getId()==null){
            logger.info("create billinfo",billinfo);
            billinfoService.insert(billinfo);
        }else {
            billinfoService.updateByPrimaryKeySelective(billinfo);
        }
        return Result.success();
    }
    @RequestMapping(value = "/billinfo/query/{billinfoid}", method = RequestMethod.POST)
    @ResponseBody
    public Object select(
            @PathVariable("billinfoid") Integer billinfoid
    ){
        logger.info("selectByPrimaryKey:"+billinfoid);
        return  billinfoService.selectByPrimaryKey(billinfoid);
    }


    @RequestMapping(value = "/billinfo/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
//            @RequestBody TaskListParam param
    ){
        List<Billinfo> billinfos  =   billinfoService.loadBillinfoList() ;
        return billinfos;
    }

//    @RequestMapping(value = "/billinfo/list", method = RequestMethod.POST)
//    @ResponseBody
//    public Object loadBillinfobyParam(
//            @RequestBody BillinfoParam param
//    ){
//        logger.info("loadBillinfobyParam:"+param);
//        Pager<Billinfo> billinfoPager = billinfoService.loadBillinfo(param) ;
//        return billinfoPager;
//    }

}