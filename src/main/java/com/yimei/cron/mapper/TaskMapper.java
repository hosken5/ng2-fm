package com.yimei.cron.mapper;

import com.github.pagehelper.Page;
import com.yimei.cron.domain.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by hongpf on 16/9/28.
 */
public interface TaskMapper {

    @Insert("INSERT  into task (taskname,description,cron,resource,creator,createtime" +
            ",timeunit,time,startHour,startMin,startSec,triggertype,off) VALUES (\n" +
            " #{taskname},#{description},#{cron},#{resource},#{creator},#{createtime}," +
            "#{timeunit},#{time},#{startHour},#{startMin},#{startSec},#{triggertype},#{off}\n" +
            " )")
    @Options(useGeneratedKeys = true)
    int addTask(Task task) ;

    @Select("select * from task where   isdelete is null  or isdelete =0  order by id desc ")
    Page<Task> loadTask();

    @Select("select * from task  where  isdelete is null  or isdelete =0  order by id desc ")
    List<Task> loadTaskList();

    @Delete("update task t  set  isdelete = 1 where  t.id= #{id}")
    int deleteTask(Task task ) ;

    @Update("update  task set status = #{status}  where  id = #{id}")
    int updateTaskStatus(@Param("id") Long id,@Param("status") int status ) ;

    @Update("update  task set off = 1  where  id = #{id}")
    int stopTask(Task task );

    @Update("update  task set off = 0  where  id = #{id}")
    int startTask(Task task );
}