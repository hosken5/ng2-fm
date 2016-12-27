package com.yimei.cron.service;

import com.yimei.cron.domain.Paymentinfo;
import com.yimei.cron.mapper.PaymentinfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongpf on 16/12/21.
 */
@Service
public class PaymentinfoService {
    Logger logger = LoggerFactory.getLogger(PaymentinfoService.class);

    @Autowired
    PaymentinfoMapper paymentinfoMapper ;


    public int deleteByPrimaryKey(Integer id){
       return  paymentinfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(Paymentinfo record){
       return paymentinfoMapper.insert(record);
    }

    public int insertSelective(Paymentinfo record){
        return  paymentinfoMapper.insertSelective(record) ;
    }

    public Paymentinfo selectByPrimaryKey(Integer id){
        return paymentinfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Paymentinfo record){
        return  paymentinfoMapper.updateByPrimaryKeySelective(record) ;
    }

    public int updateByPrimaryKey(Paymentinfo record){
        return paymentinfoMapper.updateByPrimaryKey(record) ;
    }


    public List<Paymentinfo> loadPaymentinfoList() {
        return paymentinfoMapper.loadPaymentinfoList();
    }

//    public Pager<Paymentinfo> loadPaymentinfo(PaymentinfoParam param) {
//        PageHelper.startPage(param.getPage(),param.getPageSize(),true);
//        return    Pager.of(paymentinfoMapper.loadPaymentinfo(param)) ;
//    }
}