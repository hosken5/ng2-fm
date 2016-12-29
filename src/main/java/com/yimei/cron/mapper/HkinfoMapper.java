package com.yimei.cron.mapper;

import com.yimei.cron.domain.Hkinfo;
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

}