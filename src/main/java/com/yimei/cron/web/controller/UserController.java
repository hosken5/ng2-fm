package com.yimei.cron.web.controller;

import com.yimei.cron.basic.annotation.LoginRequired;
import com.yimei.cron.basic.common.Result;
import com.yimei.cron.domain.Menu;
import com.yimei.cron.domain.User;
import com.yimei.cron.service.Session;
import com.yimei.cron.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hongpf on 16/12/31.
 */
@Controller
@LoginRequired
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Session session ;

    @Autowired
    private UserService  userService ;

    private static HashMap<String, List<Menu>> rolemenus  =  new HashMap();

    static {
        rolemenus.put("0",new ArrayList<Menu>(){{
            add(new Menu("/coalselllist","销售信息管理"));
            add(new Menu("/teamlist","团队管理"));
            add(new Menu("/financecell","财务实体管理"));
            add(new Menu("/userlist","用户管理"));
        }});
        rolemenus.put("1",new ArrayList<Menu>(){{
            add(new Menu("/coalselllist","销售信息管理"));
            add(new Menu("/teamlist","团队管理"));
            add(new Menu("/financecell","财务实体管理"));
        }});
        rolemenus.put("2",new ArrayList<Menu>(){{
            add(new Menu("/coalselllist","销售信息管理"));
        }});
    }


    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
    ){
        logger.info("user/list called...");
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

    @RequestMapping( value = "/")
    Object   index1 (){
        if (session.isLogined()) {
            return  "index";
        } else  {
            return  "login";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    String login1(){
        return  "login" ;
    }


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam("loginname") String loginName,
                        @RequestParam("plainpassword") String plainpassword) {
        User user = userService.validateLogin(loginName, plainpassword);
        if (user == null) {
            return Result.error("用户名不存在");
        }else if (user.getActivated() == 0){
            return  Result.error("用户已经被禁用");
        }
        session.login(user);
        return  Result.success();
    }

    @ResponseBody
    @RequestMapping(value = "/user/session", method = RequestMethod.POST)
    public Object sessionuser() {
        return session.getUser();
    }

    @ResponseBody
    @RequestMapping(value = "/user/menu", method = RequestMethod.POST)
    public Object usermenu() {
        return  rolemenus.get(session.getUser().getRole());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        session.logout();
        return "/login";
    }


}
