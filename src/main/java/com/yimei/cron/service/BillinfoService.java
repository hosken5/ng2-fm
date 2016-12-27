package com.yimei.cron.service;

import com.yimei.cron.domain.Billinfo;
import com.yimei.cron.mapper.BillinfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongpf on 16/12/21.
 */
@Service
public class BillinfoService {
    Logger logger = LoggerFactory.getLogger(BillinfoService.class);

    @Autowired
    BillinfoMapper billinfoMapper ;


    public int deleteByPrimaryKey(Integer id){
       return  billinfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(Billinfo record){
       return billinfoMapper.insert(record);
    }

    public int insertSelective(Billinfo record){
        return  billinfoMapper.insertSelective(record) ;
    }

    public Billinfo selectByPrimaryKey(Integer id){
        return billinfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Billinfo record){
        return  billinfoMapper.updateByPrimaryKeySelective(record) ;
    }

    public int updateByPrimaryKey(Billinfo record){
        return billinfoMapper.updateByPrimaryKey(record) ;
    }

    public List<Billinfo> loadBillinfoList() {
        return billinfoMapper.loadBillinfoList();
    }

//    public Pager<Billinfo> loadBillinfo(BillinfoParam param) {
//        PageHelper.startPage(param.getPage(),param.getPageSize(),true);
//        return    Pager.of(billinfoMapper.loadBillinfo(param)) ;
//    }
}