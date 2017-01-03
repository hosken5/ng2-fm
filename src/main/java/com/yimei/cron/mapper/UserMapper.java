package com.yimei.cron.mapper;

import com.yimei.cron.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loadByLoginName(String loginName);

    void updatePassword(
            @Param("password") String password,
            @Param("passwordsalt") String passwordsalt,
            @Param("userid") Long userid
    );

    @Select("select * from user order by id ")
    List<User> loadUserList();
}