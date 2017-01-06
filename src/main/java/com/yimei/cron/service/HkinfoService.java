package com.yimei.cron.service;

import com.yimei.cron.domain.Hkinfo;
import com.yimei.cron.domain.Income;
import com.yimei.cron.mapper.HkinfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public List<Hkinfo> loadHkinfoListByCoalsellId(Integer coalsellid) {
       return hkinfoMapper.loadHkinfoListByCoalsellId(coalsellid);
    }

    public List<Income> loadIncomeListByCoalsellId(Integer coalsellid) {

        BigDecimal sybj   ;

        BigDecimal sum   = BigDecimal.valueOf(0) ;

        List<Income> fkinfos = hkinfoMapper.loadFkinfoListByCoalsellId(coalsellid);

        List<Hkinfo>  hkinfos   =  hkinfoMapper.loadHkinfoListByCoalsellId(coalsellid);

        List<Income>  result = new ArrayList<>() ;


        for ( Income fkinfo:fkinfos ){
            sum = sum.add(fkinfo.getFkje());
        }
        sybj = sum  ;
        for (int i = 0; i <fkinfos.size()&&i<hkinfos.size(); i++) {
            Income fkinfo = fkinfos.get(i) ;
            Hkinfo  hkinfo = hkinfos.get(i) ;
            BigDecimal margin =fkinfo.getFkje().subtract(hkinfo.getHkje());
            if(margin.doubleValue()>0){
                fkinfo.setFkje(hkinfo.getHkje());
                Income fkmargin =  new Income(fkinfo) ;
                fkmargin.setFkje(margin);
                fkinfos.add(i+1,fkmargin);
            }else if (margin.doubleValue()<0) {
                hkinfo.setHkje(fkinfo.getFkje());
                Hkinfo info =  new Hkinfo();
                info.setHkje(margin.abs());
                info.setHkrq(hkinfo.getHkrq());
                info.setHkfs(hkinfo.getHkfs());
                info.setBz(hkinfo.getBz());
                hkinfos.add(i+1,info);
            }
        }

        for (int i = 0; i < fkinfos.size(); i++) {
            Income in = new Income();
            if(hkinfos.size()<=i){
                in.setFkje(fkinfos.get(i).getFkje());
                in.setFkrq(fkinfos.get(i).getFkrq());
                in.setFktype(fkinfos.get(i).getFktype());
//                in.setHkje(BigDecimal.valueOf(0));
            }else {
                in.setFkje(fkinfos.get(i).getFkje());
                in.setFkrq(fkinfos.get(i).getFkrq());
                in.setFktype(fkinfos.get(i).getFktype());
                in.setHkje(hkinfos.get(i).getHkje());
                in.setHkrq(hkinfos.get(i).getHkrq());
                sybj = sybj.subtract(in.getHkje()) ;
                in.setSybj(sybj);
            }
            result.add(in);
        }
        return  result  ;
//        return hkinfoMapper.loadIncomeListByCoalsellId(coalsellid);

    }

//    public Pager<Hkinfo> loadHkinfo(HkinfoParam param) {
//        PageHelper.startPage(param.getPage(),param.getPageSize(),true);
//        return    Pager.of(hkinfoMapper.loadHkinfo(param)) ;
//    }
}