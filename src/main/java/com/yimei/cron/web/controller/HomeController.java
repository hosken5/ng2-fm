package com.yimei.cron.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 路由配置
 * Created by hongpf on 16/7/21.
 */
@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping( value = "/")
    Object   index (){
        return "index";
    }

    @RequestMapping( value = "/sample/**")
    Object sample (
            ){
        return "sample";
    }
}