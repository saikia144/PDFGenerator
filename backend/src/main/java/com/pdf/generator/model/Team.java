package com.pdf.generator.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "team_id")
	private Integer tid;
	
	@Column(name = "team_name")
	private String name;
	
	@Column(name = "team_logo")
	private String logo;
	
    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL)
	private TeamStatistics teamStatistics;
	
	public Team() {	}
	
	public Team(Integer tid, String name, String logo) {
		this.tid = tid;
		this.name = name;
		this.logo = logo;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public TeamStatistics getTeamStatistics() {
		return teamStatistics;
	}

	public void setTeamStatistics(TeamStatistics teamStatistics) {
		this.teamStatistics = teamStatistics;
	}
}
