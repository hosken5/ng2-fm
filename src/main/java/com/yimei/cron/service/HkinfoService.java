package com.yimei.cron.service;

import com.yimei.cron.domain.Hkinfo;
import com.yimei.cron.mapper.HkinfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongpf on 16/12/21.
 */
@Service
public class HkinfoService {
    Logger logger = LoggerFactory.getLogger(HkinfoService.class);

    @Autowired
    HkinfoMapper hkinfoMapper ;


    public int deleteByPrimaryKey(Integer id){
       return  hkinfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(Hkinfo record){
       return hkinfoMapper.insert(record);
    }

    public int insertSelective(Hkinfo record){
        return  hkinfoMapper.insertSelective(record) ;
    }

    public Hkinfo selectByPrimaryKey(Integer id){
        return hkinfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Hkinfo record){
        return  hkinfoMapper.updateByPrimaryKeySelective(record) ;
    }

    public int updateByPrimaryKey(Hkinfo record){
        return hkinfoMapper.updateByPrimaryKey(record) ;
    }

    public List<Hkinfo> loadHkinfoList() {
        return hkinfoMapper.loadHkinfoList();
    }

//    public Pager<Hkinfo> loadHkinfo(HkinfoParam param) {
//        PageHelper.startPage(param.getPage(),param.getPageSize(),true);
//        return    Pager.of(hkinfoMapper.loadHkinfo(param)) ;
//    }
}