package com.yimei.cron.mapper;

import com.github.pagehelper.Page;
import com.yimei.cron.domain.TaskLog;
import com.yimei.cron.domain.Team;
import com.yimei.cron.web.dto.TaskListParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by hongpf on 16/12/16.
 */
public interface TeamMapper {

    @Insert("INSERT  into team (taskid,taskname,starttime,endtime,message) values (" +
            "#{taskid},#{taskname},#{starttime},#{endtime},#{message})" )
    @Options(useGeneratedKeys = true)
    int addTeam(Team taskLog) ;

    @Select("select * from teaminfo  where  taskid = #{taskid} order by id desc ")
    Page<Team> loadTeam(TaskListParam param);

    @Select("select * from teaminfo")
    List<Team> loadTeamList() ;

    @Update("update  tasklog t set  endtime= #{endtime},  message=#{message}, reqsuccess=#{reqsuccess} where id = #{id}")
    int addTaskLogEnd(TaskLog taskLog);
}
