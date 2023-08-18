package com.pdf.generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdf.generator.model.Team;
import com.pdf.generator.model.TeamStatistics;

public interface TeamStatisticsDao extends JpaRepository<TeamStatistics, Integer>{
    TeamStatistics findByTeam(Team team);

}
