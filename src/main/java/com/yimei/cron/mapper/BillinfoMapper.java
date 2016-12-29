package com.yimei.cron.mapper;

import com.yimei.cron.domain.Billinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BillinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Billinfo record);

    int insertSelective(Billinfo record);

    Billinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Billinfo record);

    int updateByPrimaryKey(Billinfo record);

    @Select("select * from billinfo ")
    List<Billinfo> loadBillinfoList();

    @Select("select * from billinfo where  coalsellid = #{coalsellid}  order by kpdate asc")
    List<Billinfo> loadBillinfoListByCoalSellId( @Param("coalsellid") Integer coalsellid);
}