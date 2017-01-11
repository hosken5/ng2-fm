package com.yimei.cron.service;

import com.yimei.cron.domain.Hkinfo;
import com.yimei.cron.domain.Income;
import com.yimei.cron.mapper.HkinfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Period;
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
            BigDecimal  khbj = getkhbj(fkinfo,hkinfo);
            BigDecimal  margin =fkinfo.getFkje().subtract(khbj);

            if(margin.doubleValue()>0){ //付款金额多,拆分付款金额
                fkinfo.setFkje(khbj);
                Income fkmargin =  new Income(fkinfo) ;
                fkmargin.setFkje(margin);

                fkinfos.add(i+1,fkmargin);

            }else if (margin.doubleValue()<0) {//回款金额多,拆分回款金额
                Double  yflx = getYflx(fkinfo,hkinfo) ;
                BigDecimal  margin1 =  hkinfo.getHkje().subtract(fkinfo.getFkje().add(BigDecimal.valueOf(yflx)));
                hkinfo.setHkje(fkinfo.getFkje().add(BigDecimal.valueOf(yflx)));
                Hkinfo info =  new Hkinfo();
                info.setHkje(margin1);
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
            }else {
                in.setFkje(fkinfos.get(i).getFkje());
                in.setFkrq(fkinfos.get(i).getFkrq());
                in.setFktype(fkinfos.get(i).getFktype());
                in.setHkje(hkinfos.get(i).getHkje());
                in.setHkrq(hkinfos.get(i).getHkrq());
                sybj = sybj.subtract(in.getFkje()) ;
                in.setSybj(sybj);
            }
            result.add(in);
        }
        return  result  ;
    }


    //获取应付利息
    private Double  getYflx(Income fkinfo, Hkinfo hkinfo) {
        Integer dual  = Period.between(hkinfo.getHkrq(),fkinfo.getFkrq()).getDays()  ;
        BigDecimal htjzll =fkinfo.getHtzjll()  ;
        Double  yflx  ;
        if(dual <=60){
            yflx =  htjzll.doubleValue()/360*fkinfo.getFkje().doubleValue()*dual ;
        }else  if ( dual <=90 ){
            yflx =   htjzll.doubleValue()/360*fkinfo.getFkje().doubleValue()* 60  +
                    (htjzll.doubleValue()+0.05)/360*fkinfo.getFkje().doubleValue()*(dual-60);
        } else  {
            yflx =   htjzll.doubleValue()/360*fkinfo.getFkje().doubleValue()* 60  +
                    (htjzll.doubleValue()+0.05)/360*fkinfo.getFkje().doubleValue()*30 +
                    (htjzll.doubleValue()+0.1)/360*fkinfo.getFkje().doubleValue()*(dual-90);
        }
        return   BigDecimal.valueOf(yflx).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() ;
    }


    //获取可还本金
    private BigDecimal  getkhbj(Income fkinfo, Hkinfo hkinfo) {
        Integer dual  = Period.between(fkinfo.getFkrq(),hkinfo.getHkrq()).getDays()  ;
        BigDecimal htjzll =fkinfo.getHtzjll() ;
        BigDecimal hkje =   hkinfo.getHkje()  ;
        BigDecimal   khbj  ;
        if(dual <=60){
            khbj =  hkje.multiply(BigDecimal.valueOf(360)).divide(
                    htjzll.multiply(BigDecimal.valueOf(dual)).add(BigDecimal.valueOf(360))
            ,2,BigDecimal.ROUND_HALF_UP) ;
        }else  if ( dual <=90 ){
            khbj = hkje.multiply(BigDecimal.valueOf(360)).divide(
            htjzll.multiply(BigDecimal.valueOf(60))
                    .add(
                        htjzll.add(BigDecimal.valueOf(0.05)).multiply(
                            BigDecimal.valueOf(dual).subtract(BigDecimal.valueOf(60))
                    ).add(BigDecimal.valueOf(360))
            ),2,BigDecimal.ROUND_HALF_UP) ;
        } else {
            khbj = hkje.multiply(BigDecimal.valueOf(360)).divide(
            htjzll.multiply(BigDecimal.valueOf(60))
                    .add (
                    htjzll.add(BigDecimal.valueOf(0.05)).multiply(
                            BigDecimal.valueOf(30)
                    ).add(
                        htjzll.add(BigDecimal.valueOf(0.1)).multiply(
                                BigDecimal.valueOf(dual-90)
                        )
                    ).add (
                            BigDecimal.valueOf(360)
                    )
            ),2,BigDecimal.ROUND_HALF_UP);
        }
        return khbj;
    }
}