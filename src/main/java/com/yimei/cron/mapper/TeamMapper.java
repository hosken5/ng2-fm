package com.yimei.cron.mapper;

import com.yimei.cron.domain.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by hongpf on 16/12/16.
 */
public interface TeamMapper {

    @Insert("INSERT  into teaminfo (name,message) values (" +
            "#{name},#{message})")
    @Options(useGeneratedKeys = true)
    int addTeam(Team team) ;

    @Select("select * from teaminfo name ")
    List<Team> loadTeamList() ;

    @Update("update teaminfo t set t.message = #{message},t.name=#{name} where id = #{id}")
    void updateTeam(Team team);
}
