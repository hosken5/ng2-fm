package com.yimei.cron.service;

import com.github.pagehelper.PageHelper;
import com.yimei.cron.domain.Coalsell;
import com.yimei.cron.mapper.CoalsellMapper;
import com.yimei.cron.util.Pager;
import com.yimei.cron.web.dto.CoalsellParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hongpf on 16/12/21.
 */
@Service
public class CoalsellService {
    Logger logger = LoggerFactory.getLogger(CoalsellService.class);

    @Autowired
    CoalsellMapper  coalsellMapper ;


    public int deleteByPrimaryKey(Integer id){
       return  coalsellMapper.deleteByPrimaryKey(id);
    }

    public int insert(Coalsell record){
       return coalsellMapper.insert(record);
    }

    public int insertSelective(Coalsell record){
        return  coalsellMapper.insertSelective(record) ;
    }

    public Coalsell selectByPrimaryKey(Integer id){
        return coalsellMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Coalsell record){
        return  coalsellMapper.updateByPrimaryKeySelective(record) ;
    }

    public int updateByPrimaryKey(Coalsell record){
        return coalsellMapper.updateByPrimaryKey(record) ;
    }

    public Pager<Coalsell> loadCoalsell(CoalsellParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize(),true);
        return    Pager.of(coalsellMapper.loadCoalsell(param)) ;
    }
}