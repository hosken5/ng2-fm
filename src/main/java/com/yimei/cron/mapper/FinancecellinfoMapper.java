package com.yimei.cron.mapper;

import com.yimei.cron.domain.Financecellinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FinancecellinfoMapper {
    int deleteByPrimaryKey(Integer id);


    @Insert("INSERT  into financecellinfo (name,message) values (" +
            "#{name},#{message})")
    @Options(useGeneratedKeys = true)
    int insert(Financecellinfo record);

    int insertSelective(Financecellinfo record);

    Financecellinfo selectByPrimaryKey(Integer id);

    @Select("select * from  financecellinfo ")
    List<Financecellinfo> selectFinancecellinfo() ;

    int updateByPrimaryKeySelective(Financecellinfo record);

    int updateByPrimaryKey(Financecellinfo record);
}