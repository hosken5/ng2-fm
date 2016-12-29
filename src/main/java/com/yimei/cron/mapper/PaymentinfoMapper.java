package com.yimei.cron.mapper;

import com.yimei.cron.domain.Paymentinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PaymentinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Paymentinfo record);

    int insertSelective(Paymentinfo record);

    Paymentinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Paymentinfo record);

    int updateByPrimaryKey(Paymentinfo record);

    @Select("select * from paymentinfo ")
    List<Paymentinfo> loadPaymentinfoList();

    @Select("select * from paymentinfo where  coalsellid = #{coalsellid}  order by fkrq asc")
    List<Paymentinfo> loadPaymentinfoListByCoalSellId( @Param("coalsellid") Integer coalsellid);
}