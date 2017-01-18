package com.yimei.cron.mapper;

import com.yimei.cron.domain.Hkinfo;
import com.yimei.cron.domain.Income;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HkinfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Hkinfo record);

    int insertSelective(Hkinfo record);

    Hkinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hkinfo record);

    int updateByPrimaryKey(Hkinfo record);

    @Select("select * from hkinfo ")
    List<Hkinfo> loadHkinfoList();

    @Select("select * from hkinfo where  coalsellid = #{coalsellid}  order by hkrq asc")
    List<Hkinfo> loadHkinfoListByCoalsellId(@Param("coalsellid") Integer coalsellid);


    @Select("select t.* , b.htzjll/100 as htzjll   from  (\n" +
            "        select jklx as jklx ,    jxts as jxts , ll/100 as ll , fkrq as jxqsr , hkrq as fkrq ,hkje as fkje   , '1' as fktype   from  paymentinfo  where coalsellid = #{coalsellid} \n" +
            "  and  hkrq is not null  and  hkje is not null " +
            "        union all\n" +
            "        select  null  as jklx , null  as jxts , null as ll ,  fkrq as jxqsr , fkrq , fkje , '2' as fktype from  paymentinfozy where  coalsellid = #{coalsellid} \n" +
            "      ) t ,coalsell b where  b.id = #{coalsellid} \n" +
            "      order by  t.fkrq, fktype")
    List<Income> loadFkinfoListByCoalsellId(Integer coalsellid);

}