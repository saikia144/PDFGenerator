package com.pdf.generator.service;

import com.pdf.generator.model.Team;
import com.pdf.generator.model.TeamStatistics;

public interface TeamStatisticsService {
	public TeamStatistics fetchAndSaveTeamStatistics(Team t,String team,String season,String league);
}
