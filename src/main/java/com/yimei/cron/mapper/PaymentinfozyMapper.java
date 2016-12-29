package com.yimei.cron.mapper;

import com.yimei.cron.domain.Paymentinfozy;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PaymentinfozyMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Paymentinfozy record);

    int insertSelective(Paymentinfozy record);

    Paymentinfozy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Paymentinfozy record);

    int updateByPrimaryKey(Paymentinfozy record);

    @Select("select * from paymentinfozy ")
    List<Paymentinfozy> loadPaymentinfozyList();

    @Select("select * from paymentinfozy where  coalsellid = #{coalsellid}  order by fkrq asc")
    List<Paymentinfozy> loadPaymentinfozyListByCoalSellId(Integer coalsellid);
}