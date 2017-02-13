package com.yimei.cron.service;

import com.yimei.cron.domain.Hkinfo;
import com.yimei.cron.domain.Income;
import com.yimei.cron.mapper.HkinfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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


        List<Income> fkinfos = hkinfoMapper.loadFkinfoListByCoalsellId(coalsellid);

        List<Hkinfo>  hkinfos   =  hkinfoMapper.loadHkinfoListByCoalsellId(coalsellid);

        List<Income>  result = new ArrayList<>() ;


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
                Double  yflx = getYflx(fkinfo,hkinfo).doubleValue() ;
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

        BigDecimal sybjsum = BigDecimal.ZERO ;

        BigDecimal sylxsum = BigDecimal.ZERO ;

        BigDecimal sywbrzlxsum = BigDecimal.ZERO ;

        for (int i = 0 ; i < fkinfos.size(); i++) {
            Income in = new Income();
            if(hkinfos.size()<=i){
                in.setJklx(fkinfos.get(i).getJklx());
                in.setJxqsr(fkinfos.get(i).getJxqsr());
                in.setFkje(fkinfos.get(i).getFkje());
                in.setFkrq(fkinfos.get(i).getFkrq());
                in.setFktype(fkinfos.get(i).getFktype());
                in.setJxts(fkinfos.get(i).getJxts());
                in.setSybj (in.getFkje()) ;
                in.setLl(fkinfos.get(i).getLl());
                in.setJklx(fkinfos.get(i).getJklx());
                in.setSywbrzlx(getSywbrzlx(in));
                in.setSylx(getYflx(in.getFkje(),fkinfos.get(i).getHtzjll(),in.getJxqsr(),in.getFkrq(),LocalDate.now()));
                sybjsum = sybjsum.add(in.getFkje()) ;
                sylxsum = sylxsum.add(in.getSylx()) ;
                sywbrzlxsum =  sywbrzlxsum.add(in.getSywbrzlx()) ;
            }else {
                in.setJxqsr(fkinfos.get(i).getJxqsr());
                in.setFkje(fkinfos.get(i).getFkje());
                in.setFkrq(fkinfos.get(i).getFkrq());
                in.setFktype(fkinfos.get(i).getFktype());
                in.setHkje(hkinfos.get(i).getHkje());
                in.setHkrq(hkinfos.get(i).getHkrq());
            }
            result.add(in);
        }

        Income sum = new Income();
        sum.setSybj(sybjsum);
        sum.setSylx(sylxsum);
        sum.setSywbrzlx(sywbrzlxsum);
        result.add(sum) ; ///最后一行合计
        return  result  ;
    }

    //计算到目前为止的剩余未还外部融资利息
    private BigDecimal getSywbrzlx(Income in) {
        BigDecimal sywbrzlx = BigDecimal.ZERO ;
        if("1".equals(in.getFktype()) && in.getJklx() ==1){ //固定期限
            Long dual = in.getJxts().longValue() - ChronoUnit.DAYS.between(in.getJxqsr(),LocalDate.now()) ;
            if(dual > 0 ){
                sywbrzlx =   in.getLl().multiply(BigDecimal.valueOf(dual)).multiply(in.getSybj());
            }
        }
        return sywbrzlx ;
    }


    private  BigDecimal getYflx( BigDecimal bj , BigDecimal ll , LocalDate jxqsr ,  LocalDate stat , LocalDate end ){
        Double  lxjs = 0.0 ;
        for (long i = ChronoUnit.DAYS.between(jxqsr,stat);
             i <  ChronoUnit.DAYS.between(jxqsr,end);
             i++){
                if(i<60) {
                    lxjs = lxjs + ll.doubleValue() / 360;
                }else if (i < 90 ) {
                    lxjs = lxjs+ ( ll.doubleValue()+0.05)/360 ;
                }else {
                    lxjs = lxjs+ ( ll.doubleValue()+0.1)/360 ;
                }
        }
        return  BigDecimal.valueOf(lxjs).multiply(bj).setScale(2,BigDecimal.ROUND_HALF_UP) ;
    }



    //获取应付利息 1.1
    private BigDecimal   getYflx(Income fkinfo, Hkinfo hkinfo) {

        BigDecimal  lxjs = BigDecimal.ZERO ;
        BigDecimal  ll ;
        BigDecimal  bj   = fkinfo.getFkje();
        BigDecimal  sywbrzlx  = BigDecimal.ZERO ; //剩余外部融资利息

        if("2".equals(fkinfo.getFktype())){ //自营计息
            for (int i = 0; i <  ChronoUnit.DAYS.between(fkinfo.getFkrq(), hkinfo.getHkrq()); i++) {
                if(i<60){
                    ll =fkinfo.getHtzjll();
                }else if (i<90) {
                    ll = fkinfo.getHtzjll().add(BigDecimal.valueOf(0.05)) ;
                }else {
                    ll = fkinfo.getHtzjll().add(BigDecimal.valueOf(0.1)) ;
                }
                lxjs = lxjs.add(ll.multiply(bj))  ;
            }
        }else {//外部融资借还款
            for (long  i = ChronoUnit.DAYS.between(fkinfo.getJxqsr(),fkinfo.getFkrq());
                 i <  ChronoUnit.DAYS.between( fkinfo.getJxqsr(), hkinfo.getHkrq());
                 i++) {
                if(i<60){
                    ll =fkinfo.getHtzjll();
                }else if (i<90) {
                    ll = fkinfo.getHtzjll().add(BigDecimal.valueOf(0.05)) ;
                }else {
                    ll = fkinfo.getHtzjll().add(BigDecimal.valueOf(0.1)) ;
                }
                lxjs = lxjs.add(ll.multiply(bj))  ;
            }
            if( fkinfo.getJklx() ==1){ //固定期限
                Long dual = fkinfo.getJxts().longValue() - ChronoUnit.DAYS.between(fkinfo.getJxqsr(),hkinfo.getHkrq()) ;
                if(dual > 0 ){
                    sywbrzlx = fkinfo.getLl().multiply(BigDecimal.valueOf(dual)).multiply(bj);
                }
            }
        }
        return lxjs.add(sywbrzlx).setScale(2,BigDecimal.ROUND_HALF_UP);
    }


    //获取可还本金 1.1
    private BigDecimal  getkhbj(Income fkinfo, Hkinfo hkinfo) {
        BigDecimal  lxjs = BigDecimal.ZERO ;
        BigDecimal  ll ;
        Double bj  = 360.0;
        BigDecimal  sywbrzlx  = BigDecimal.ZERO ; //剩余外部融资利息
        if("2".equals(fkinfo.getFktype())){ //自营计息
            for (int i = 0; i <  ChronoUnit.DAYS.between( fkinfo.getFkrq(), hkinfo.getHkrq()); i++) {
                if(i<60){
                    ll =fkinfo.getHtzjll();
                }else if (i<90) {
                    ll = fkinfo.getHtzjll().add(BigDecimal.valueOf(0.05)) ;
                }else {
                    ll = fkinfo.getHtzjll().add(BigDecimal.valueOf(0.1)) ;
                }
                lxjs = lxjs.add(ll);
            }
        }else {//外部融资借还款
            for (long  i =ChronoUnit.DAYS.between(fkinfo.getJxqsr(),fkinfo.getFkrq());
                 i <  ChronoUnit.DAYS.between(fkinfo.getJxqsr(),hkinfo.getHkrq());
                 i++) {
                if(i<60){
                    ll =fkinfo.getHtzjll();
                }else if (i<90) {
                    ll = fkinfo.getHtzjll().add(BigDecimal.valueOf(0.05)) ;
                }else {
                    ll = fkinfo.getHtzjll().add(BigDecimal.valueOf(0.1));
                }
                lxjs = lxjs.add(ll)  ;
            }

            if( fkinfo.getJklx() ==1){ //固定期限
                Long dual = fkinfo.getJxts().longValue() - ChronoUnit.DAYS.between(fkinfo.getJxqsr(),hkinfo.getHkrq()) ;
                if(dual > 0 ){
                    sywbrzlx = fkinfo.getLl().multiply(BigDecimal.valueOf(dual));

                }
            }
        }
        return hkinfo.getHkje().multiply(BigDecimal.valueOf(360)).divide(
                BigDecimal.valueOf(360).add(lxjs).add(sywbrzlx),2,BigDecimal.ROUND_HALF_UP
        );
    }





//    //获取可还本金 1.1
//    private BigDecimal  getkhbj(Income fkinfo, Hkinfo hkinfo) {
//        BigDecimal  lxjs = BigDecimal.ZERO ;
//        BigDecimal  ll ;
//        Double bj  = 1.0;
//        for (int i = 0; i <  Period.between( fkinfo.getFkrq(), hkinfo.getHkrq()).getDays(); i++) {
//            if(i<60){
//                ll =fkinfo.getLl();
//            }else if (i<90) {
//                ll = fkinfo.getLl().add(BigDecimal.valueOf(0.05)) ;
//            }else {
//                ll = fkinfo.getLl().add(BigDecimal.valueOf(0.1)) ;
//            }
//            if(i<fkinfo.getJxts().intValue()){
//                bj = 360.0 ;
//            }else {
//               bj = 360+getYflx(BigDecimal.valueOf(360),fkinfo.getLl(),fkinfo.getJxts().intValue(),fkinfo.getJxts().intValue()).doubleValue() ;
//            }
//            lxjs = lxjs.add(ll.multiply(BigDecimal.valueOf(bj)))  ;
//        }
//        return hkinfo.getHkje().multiply(BigDecimal.valueOf(360)).divide(
//               BigDecimal.valueOf(360).add(lxjs)
//               ).setScale(2,BigDecimal.ROUND_HALF_UP) ;


//        Integer dual  = Period.between(fkinfo.getFkrq(),hkinfo.getHkrq()).getDays()  ;
//        BigDecimal htjzll =fkinfo.getHtzjll() ;
//        BigDecimal hkje =   hkinfo.getHkje()  ;
//        BigDecimal   khbj  ;
//        if(dual <=60){
//            khbj =  hkje.multiply(BigDecimal.valueOf(360)).divide(
//                    htjzll.multiply(BigDecimal.valueOf(dual)).add(BigDecimal.valueOf(360))
//                    ,2,BigDecimal.ROUND_HALF_UP) ;
//        }else  if ( dual <=90 ){
//            khbj = hkje.multiply(BigDecimal.valueOf(360)).divide(
//                    htjzll.multiply(BigDecimal.valueOf(60))
//                            .add(
//                                    htjzll.add(BigDecimal.valueOf(0.05)).multiply(
//                                            BigDecimal.valueOf(dual).subtract(BigDecimal.valueOf(60))
//                                    ).add(BigDecimal.valueOf(360))
//                            ),2,BigDecimal.ROUND_HALF_UP) ;
//        } else {
//            khbj = hkje.multiply(BigDecimal.valueOf(360)).divide(
//                    htjzll.multiply(BigDecimal.valueOf(60))
//                            .add (
//                                    htjzll.add(BigDecimal.valueOf(0.05)).multiply(
//                                            BigDecimal.valueOf(30)
//                                    ).add(
//                                            htjzll.add(BigDecimal.valueOf(0.1)).multiply(
//                                                    BigDecimal.valueOf(dual-90)
//                                            )
//                                    ).add (
//                                            BigDecimal.valueOf(360)
//                                    )
//                            ),2,BigDecimal.ROUND_HALF_UP);
//        }
//        return khbj;




//
//    //获取可还本金
//    private BigDecimal  getkhbj(Income fkinfo, Hkinfo hkinfo) {
//        Integer dual  = Period.between(fkinfo.getFkrq(),hkinfo.getHkrq()).getDays()  ;
//        BigDecimal htjzll =fkinfo.getHtzjll() ;
//        BigDecimal hkje =   hkinfo.getHkje()  ;
//        BigDecimal   khbj  ;
//        if(dual <=60){
//            khbj =  hkje.multiply(BigDecimal.valueOf(360)).divide(
//                    htjzll.multiply(BigDecimal.valueOf(dual)).add(BigDecimal.valueOf(360))
//            ,2,BigDecimal.ROUND_HALF_UP) ;
//        }else  if ( dual <=90 ){
//            khbj = hkje.multiply(BigDecimal.valueOf(360)).divide(
//            htjzll.multiply(BigDecimal.valueOf(60))
//                    .add(
//                        htjzll.add(BigDecimal.valueOf(0.05)).multiply(
//                            BigDecimal.valueOf(dual).subtract(BigDecimal.valueOf(60))
//                    ).add(BigDecimal.valueOf(360))
//            ),2,BigDecimal.ROUND_HALF_UP) ;
//        } else {
//            khbj = hkje.multiply(BigDecimal.valueOf(360)).divide(
//            htjzll.multiply(BigDecimal.valueOf(60))
//                    .add (
//                    htjzll.add(BigDecimal.valueOf(0.05)).multiply(
//                            BigDecimal.valueOf(30)
//                    ).add(
//                        htjzll.add(BigDecimal.valueOf(0.1)).multiply(
//                                BigDecimal.valueOf(dual-90)
//                        )
//                    ).add (
//                            BigDecimal.valueOf(360)
//                    )
//            ),2,BigDecimal.ROUND_HALF_UP);
//        }
//        return khbj;
//    }
}