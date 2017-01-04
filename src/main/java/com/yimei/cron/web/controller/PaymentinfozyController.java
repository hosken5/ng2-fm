package com.yimei.cron.web.controller;

import com.yimei.cron.basic.annotation.LoginRequired;
import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Paymentinfozy;
import com.yimei.cron.service.PaymentinfozyService;
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
public class PaymentinfozyController {

    Logger logger = LoggerFactory.getLogger(PaymentinfozyController.class);

    @Autowired
    PaymentinfozyService paymentinfozyzyService;


    /**
     * add or update
     * @param
     * @returnpaymentinfozy
     */

    @RequestMapping(value = "/paymentinfozy/addorupdate", method = RequestMethod.POST)
    @ResponseBody
    public Object add(
            @RequestBody Paymentinfozy paymentinfozy
    ){
        if(paymentinfozy.getId()==null){
            logger.info("create paymentinfozy",paymentinfozy);
            paymentinfozyzyService.insert(paymentinfozy);
        }else {
            logger.info("update paymentinfozy",paymentinfozy);
            paymentinfozyzyService.updateByPrimaryKeySelective(paymentinfozy);
        }
        return Result.success();
    }
    @RequestMapping(value = "/paymentinfozy/query/{paymentinfozyid}", method = RequestMethod.POST)
    @ResponseBody
    public Object select(
            @PathVariable("paymentinfozyid") Integer paymentinfozyid
    ){
        logger.info("selectByPrimaryKey:"+paymentinfozyid);
        return  paymentinfozyzyService.selectByPrimaryKey(paymentinfozyid);
    }


    @RequestMapping(value = "/paymentinfozy/listbycoalsellid/{coalsellid}", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
            @PathVariable ("coalsellid")  Integer coalsellid
    ){
        List<Paymentinfozy> paymentinfozys  =   paymentinfozyzyService.loadPaymentinfozyListByCoalSellId(coalsellid) ;
        return paymentinfozys;
    }
}