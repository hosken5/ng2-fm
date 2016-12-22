package com.yimei.cron.web.controller;

import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Financecellinfo;
import com.yimei.cron.service.FinancecellinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hongpf on 16/12/15.
 */
@Controller
public class FinancecellinfoController {

    Logger logger = LoggerFactory.getLogger(FinancecellinfoController.class);

    @Autowired
    FinancecellinfoService financecellinfoService ;

    @RequestMapping(value = "/financecell/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
    ){
       List<Financecellinfo> financecellinfos  =   financecellinfoService.loadFinancecellinfoList() ;
       return financecellinfos;
    }

    @RequestMapping(value = "/financecell/addorupdate", method = RequestMethod.POST)
    @ResponseBody
    public Object addFinancecellinfo(
            @RequestBody Financecellinfo financecellinfo
    ){
        logger.info(" financecellinfo:info"+financecellinfo);
        if(financecellinfo.getId() ==null ){
            financecellinfoService.addFinancecellinfo(financecellinfo); ;
        }else {
            financecellinfoService.updateFinancecellinfo(financecellinfo); ;
        }
        return Result.success();
    }

}
