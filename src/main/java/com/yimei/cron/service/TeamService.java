package com.yimei.cron.service;

import com.yimei.cron.domain.Team;
import com.yimei.cron.mapper.TeamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hongpf on 16/12/16.
 */
@Service
public class TeamService {

    Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    TeamMapper teamMapper ;

    public List<Team> loadTeamList(){
        return teamMapper.loadTeamList();
    }

    public void addTeam(Team team) {
        teamMapper.addTeam(team);
    }

    public void updateTeam(Team team) {
        teamMapper.updateTeam(team);
    }
}
