package com.pdf.generator.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.pdf.generator.model.Team;
import com.pdf.generator.model.TeamStatistics;
import com.pdf.generator.repository.TeamDao;
import com.pdf.generator.repository.TeamStatisticsDao;

@Service
@Transactional
public class TeamServiceImpl implements TeamService{
	@Autowired
	private TeamDao tdao;
	
	@Autowired
	private TeamStatisticsDao tsDao;
	
	@Autowired
	private TeamStatisticsService tstat;
	
	@Override
	public Team saveTeam(JsonNode teams,String team,String season,String league) {
		Integer tid = teams.get("id").asInt();
		String name = teams.get("name").asText();
        String logo = teams.get("logo").asText();
        
        Team t = new Team(tid, name, logo);
        
        if(isTeamPresent(t)) {
        	//System.out.println("team already in database");
        	return tdao.findByTid(tid);
        }else {
        	tdao.save(t);
        	System.out.println("saved Team");
        	
        	TeamStatistics ts = tstat.fetchAndSaveTeamStatistics(t,team,season,league);
        	
        	tsDao.save(ts);
        	return t;
        }

	}
	
	boolean isTeamPresent(Team t) {
		if(tdao.findByTid(t.getTid()) == null) {
			return false;
		}
		return true;
		
	}
	
}
