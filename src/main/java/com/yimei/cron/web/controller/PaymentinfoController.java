package com.yimei.cron.web.controller;

import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Paymentinfo;
import com.yimei.cron.service.PaymentinfoService;
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
public class PaymentinfoController {

    Logger logger = LoggerFactory.getLogger(PaymentinfoController.class);

    @Autowired
    PaymentinfoService paymentinfoService;


    /**
     * add or update
     * @param
     * @returnpaymentinfo
     */

    @RequestMapping(value = "/paymentinfo/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
            @RequestBody Paymentinfo paymentinfo
    ){
        if(paymentinfo.getId()==null){
            logger.info("create paymentinfo",paymentinfo);
            paymentinfoService.insert(paymentinfo);
        }else {
            paymentinfoService.updateByPrimaryKeySelective(paymentinfo);
        }
        return Result.success();
    }
    @RequestMapping(value = "/paymentinfo/query/{paymentinfoid}", method = RequestMethod.POST)
    @ResponseBody
    public Object select(
            @PathVariable("paymentinfoid") Integer paymentinfoid
    ){
        logger.info("selectByPrimaryKey:"+paymentinfoid);
        return  paymentinfoService.selectByPrimaryKey(paymentinfoid);
    }


    @RequestMapping(value = "/paymentinfo/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
//            @RequestBody TaskListParam param
    ){
        List<Paymentinfo> paymentinfos  =   paymentinfoService.loadPaymentinfoList() ;
        return paymentinfos;
    }

//    @RequestMapping(value = "/paymentinfo/list", method = RequestMethod.POST)
//    @ResponseBody
//    public Object loadPaymentinfobyParam(
//            @RequestBody PaymentinfoParam param
//    ){
//        logger.info("loadPaymentinfobyParam:"+param);
//        Pager<Paymentinfo> paymentinfoPager = paymentinfoService.loadPaymentinfo(param) ;
//        return paymentinfoPager;
//    }

}