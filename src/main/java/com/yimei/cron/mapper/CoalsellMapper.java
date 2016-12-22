package com.yimei.cron.mapper;

import com.github.pagehelper.Page;
import com.yimei.cron.domain.Coalsell;
import com.yimei.cron.web.dto.CoalsellParam;

public interface CoalsellMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Coalsell record);

    int insertSelective(Coalsell record);

    Coalsell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coalsell record);

    int updateByPrimaryKey(Coalsell record);

    Page<Coalsell> loadCoalsell(CoalsellParam param);
}