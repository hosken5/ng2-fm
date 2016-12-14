package com.yimei.cron.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiangyang on 16/7/29.
 */
@Controller
public class ErrorController {

    @RequestMapping("/404")
    public String error404() {
        return "index";
    }


    @RequestMapping("/400")
    public String error400() {
        return "/error/400";
    }

    @RequestMapping("/500")
    public String error500() {
        return "/error/500";
    }

}
