package com.yimei.cron.mapper;

import com.github.pagehelper.Page;
import com.yimei.cron.domain.Coalbuy;
import com.yimei.cron.web.dto.TaskListParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hongpf on 16/12/15.
 */
public interface CoalbuyMapper {

    @Insert("INSERT  into tasklog (taskid,taskname,starttime,endtime,message) values (" +
            "#{taskid},#{taskname},#{starttime},#{endtime},#{message})" )
    @Options(useGeneratedKeys = true)
    int addCoalbuy(Coalbuy coalbuy) ;

    @Select("select * from coalbuy order by id desc ")
    Page<Coalbuy> loadCoalbuy(String  param);


    @Select("select * from tasklog")
    List<Coalbuy> loadTaskLogList();

    Page loadTaskLog(TaskListParam param);
}
