package com.yimei.cron.web.controller;

import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.User;
import com.yimei.cron.service.Session;
import com.yimei.cron.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hongpf on 16/12/31.
 */
@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(TeamController.class);



    @Autowired
    private Session session ;

    @Autowired
    private UserService  userService ;



    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
    ){
        List<User> userinfos  =   userService.loadUserList() ;
        return userinfos;
    }

    @RequestMapping(value = "/user/addorupdate", method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(
            @RequestBody User userinfo
    ){
        logger.info(" userinfo:info"+userinfo);
        if(userinfo.getId() ==null ){
            userService.addUser(userinfo); ;
        }else {
            userService.updateUser(userinfo); ;
        }
        return Result.success();
    }



    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public String login(){
        return  "user/login" ;
    }


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam("loginname") String loginName,
                        @RequestParam("plainpassword") String plainpassword) {
        User user = userService.validateLogin(loginName, plainpassword);
        if (user == null) {
            return Result.error();
        }
        session.login(user);
        return  Result.success();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        session.logout();
        return "/account-manage/accountLogin";
    }


}
