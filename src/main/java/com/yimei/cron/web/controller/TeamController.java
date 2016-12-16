package com.yimei.cron.web.controller;

import com.yimei.cron.domain.Team;
import com.yimei.cron.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hongpf on 16/12/15.
 */
@Controller
public class TeamController {

    Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    TeamService teamService ;


    @RequestMapping(value = "/team/list", method = RequestMethod.POST)
    @ResponseBody
    public Object index(
//            @RequestBody TaskListParam param
    ){
       List<Team> teams  =   teamService.loadTeamList() ;
       return teams;
    }

}
