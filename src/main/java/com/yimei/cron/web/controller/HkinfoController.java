package com.yimei.cron.web.controller;

import com.yimei.cron.basic.annotation.LoginRequired;
import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Hkinfo;
import com.yimei.cron.domain.Income;
import com.yimei.cron.service.HkinfoService;
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
@LoginRequired
public class HkinfoController {

    Logger logger = LoggerFactory.getLogger(HkinfoController.class);

    @Autowired
    HkinfoService hkinfoService ;


    /**
     * add or update
     * @param hkinfo
     * @return
     */

    @RequestMapping(value = "/hkinfo/addorupdate", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
            @RequestBody Hkinfo hkinfo
    ){
        if(hkinfo.getId()==null){
            logger.info("create hkinfo",hkinfo);
            hkinfoService.insert(hkinfo);
        }else {
            logger.info("update hkinfo",hkinfo);
            hkinfoService.updateByPrimaryKey(hkinfo);
        }
        return Result.success();
    }
    @RequestMapping(value = "/hkinfo/query/{hkinfoid}", method = RequestMethod.POST)
    @ResponseBody
    public Object select(
            @PathVariable("hkinfoid") Integer hkinfoid
    ){
        logger.info("selectByPrimaryKey:"+hkinfoid);
        return  hkinfoService.selectByPrimaryKey(hkinfoid);
    }



    @RequestMapping(value = "/hkinfo/listbycoalsellid/{coalsellid}", method = RequestMethod.POST)
    @ResponseBody
    public Object listbycoalsellid(
            @PathVariable("coalsellid") Integer coalsellid
    ){
        List<Hkinfo> hkinfos  =   hkinfoService.loadHkinfoListByCoalsellId(coalsellid) ;
        return hkinfos;
    }



    @RequestMapping(value = "/income/listbycoalsellid/{coalsellid}", method = RequestMethod.POST)
    @ResponseBody
    public Object listincomebycoalsellid(
            @PathVariable("coalsellid") Integer coalsellid
    ){
        List<Income> incomes  =   hkinfoService.loadIncomeListByCoalsellId(coalsellid) ;
        return incomes;
    }

    @RequestMapping(value = "/hkinfo/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
//            @RequestBody TaskListParam param
    ){
        List<Hkinfo> hkinfos  =   hkinfoService.loadHkinfoList() ;
        return hkinfos;
    }

//    @RequestMapping(value = "/hkinfo/list", method = RequestMethod.POST)
//    @ResponseBody
//    public Object loadHkinfobyParam(
//            @RequestBody HkinfoParam param
//    ){
//        logger.info("loadHkinfobyParam:"+param);
//        Pager<Hkinfo> hkinfoPager = hkinfoService.loadHkinfo(param) ;
//        return hkinfoPager;
//    }

}