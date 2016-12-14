package com.yimei.cron.mapper;

import com.github.pagehelper.Page;
import com.yimei.cron.domain.TaskLog;
import com.yimei.cron.web.dto.TaskListParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by hongpf on 16/10/10.
 */
public interface TaskLogMapper {

    @Insert("INSERT  into tasklog (taskid,taskname,starttime,endtime,message) values (" +
            "#{taskid},#{taskname},#{starttime},#{endtime},#{message})" )
    @Options(useGeneratedKeys = true)
    int addTaskLog(TaskLog taskLog) ;

    @Select("select * from tasklog  where  taskid = #{taskid} order by id desc ")
    Page<TaskLog> loadTaskLog(TaskListParam param);

    @Select("select * from tasklog")
    List<TaskLog> loadTaskLogList();

    @Update("update  tasklog t set  endtime= #{endtime},  message=#{message}, reqsuccess=#{reqsuccess} where id = #{id}")
    int addTaskLogEnd(TaskLog taskLog);

}