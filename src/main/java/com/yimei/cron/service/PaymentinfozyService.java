package com.yimei.cron.service;

import com.yimei.cron.domain.Paymentinfozy;
import com.yimei.cron.mapper.PaymentinfozyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongpf on 16/12/21.
 */
@Service
public class PaymentinfozyService {
    Logger logger = LoggerFactory.getLogger(PaymentinfozyService.class);

    @Autowired
    PaymentinfozyMapper paymentinfoMapper ;


    public int deleteByPrimaryKey(Integer id){
       return  paymentinfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(Paymentinfozy record){
       return paymentinfoMapper.insert(record);
    }

    public int insertSelective(Paymentinfozy record){
        return  paymentinfoMapper.insertSelective(record) ;
    }

    public Paymentinfozy selectByPrimaryKey(Integer id){
        return paymentinfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Paymentinfozy record){
        return  paymentinfoMapper.updateByPrimaryKeySelective(record) ;
    }

    public int updateByPrimaryKey(Paymentinfozy record){
        return paymentinfoMapper.updateByPrimaryKey(record) ;
    }


    public List<Paymentinfozy> loadPaymentinfozyList() {
        return paymentinfoMapper.loadPaymentinfozyList();
    }

    public List<Paymentinfozy> loadPaymentinfozyListByCoalSellId(Integer coalsellid) {
        return paymentinfoMapper.loadPaymentinfozyListByCoalSellId(coalsellid);
        //
    }
}