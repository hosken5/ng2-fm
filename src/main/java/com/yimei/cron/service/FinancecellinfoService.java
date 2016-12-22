package com.yimei.cron.service;

import com.yimei.cron.domain.Financecellinfo;
import com.yimei.cron.mapper.FinancecellinfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongpf on 16/12/16.
 */
@Service
public class FinancecellinfoService {

    Logger logger = LoggerFactory.getLogger(FinancecellinfoService.class);

    @Autowired
    FinancecellinfoMapper financecellinfoMapper ;

    public List<Financecellinfo> loadFinancecellinfoList(){
        return financecellinfoMapper.selectFinancecellinfo();
    }

    public void addFinancecellinfo(Financecellinfo team) {
        financecellinfoMapper.insert(team);
    }

    public void updateFinancecellinfo(Financecellinfo financecellinfo) {
        financecellinfoMapper.updateByPrimaryKeySelective(financecellinfo) ;
    }
}
