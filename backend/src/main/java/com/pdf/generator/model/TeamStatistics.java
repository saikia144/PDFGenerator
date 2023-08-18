package com.pdf.generator.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.JsonNode;

//https://v3.football.api-sports.io/teams/statistics
@Entity
public class TeamStatistics {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@OneToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@Column(name = "playedHome")
	private int playedHome;
	
	@Column(name = "playedAway")
    private int playedAway;
	
	@Column(name = "total")
    private int playedTotal;
    
	@Column(name="winsHome")
    private int winsHome;
	
	@Column(name="winsAway")
    private int winsAway;
	
	@Column(name="winsTotal")
    private int winsTotal;
    
	@Column(name="drawsHome")
    private int drawsHome;
	@Column(name="drawsAway")
    private int drawsAway;
	@Column(name="drawsTotal")
    private int drawsTotal;

	@Column(name="losesHome")
    private int losesHome;
	
	@Column(name="losesAway")
    private int losesAway;
	
	@Column(name="losesTotal")
    private int losesTotal;

	@Column(name="goalsForHome")
    private int goalsForHome;
	
	@Column(name="goalsForAway")
    private int goalsForAway;
	
	@Column(name="goalsForTotal")
    private int goalsForTotal;

	@Column(name="goalsAgainstHome")
    private int goalsAgainstHome;
	
	@Column(name="goalsAgainstAway")
    private int goalsAgainstAway;
	
	@Column(name="goalsAgainstTotal")
    private int goalsAgainstTotal;
    
    
    public TeamStatistics() {}
    
  
	public int getPlayedHome() {
		return playedHome;
	}

	public void setPlayedHome(int playedHome) {
		this.playedHome = playedHome;
	}

	public int getPlayedAway() {
		return playedAway;
	}

	public void setPlayedAway(int playedAway) {
		this.playedAway = playedAway;
	}

	public int getPlayedTotal() {
		return playedTotal;
	}

	public void setPlayedTotal(int playedTotal) {
		this.playedTotal = playedTotal;
	}

	public int getWinsHome() {
		return winsHome;
	}

	public void setWinsHome(int winsHome) {
		this.winsHome = winsHome;
	}

	public int getWinsAway() {
		return winsAway;
	}

	public void setWinsAway(int winsAway) {
		this.winsAway = winsAway;
	}

	public int getWinsTotal() {
		return winsTotal;
	}

	public void setWinsTotal(int winsTotal) {
		this.winsTotal = winsTotal;
	}

	public int getDrawsHome() {
		return drawsHome;
	}

	public void setDrawsHome(int drawsHome) {
		this.drawsHome = drawsHome;
	}

	public int getDrawsAway() {
		return drawsAway;
	}

	public void setDrawsAway(int drawsAway) {
		this.drawsAway = drawsAway;
	}

	public int getDrawsTotal() {
		return drawsTotal;
	}

	public void setDrawsTotal(int drawsTotal) {
		this.drawsTotal = drawsTotal;
	}

	public int getLosesHome() {
		return losesHome;
	}

	public void setLosesHome(int losesHome) {
		this.losesHome = losesHome;
	}

	public int getLosesAway() {
		return losesAway;
	}

	public void setLosesAway(int losesAway) {
		this.losesAway = losesAway;
	}

	public int getLosesTotal() {
		return losesTotal;
	}

	public void setLosesTotal(int losesTotal) {
		this.losesTotal = losesTotal;
	}

	public int getGoalsForHome() {
		return goalsForHome;
	}

	public void setGoalsForHome(int goalsForHome) {
		this.goalsForHome = goalsForHome;
	}

	public int getGoalsForAway() {
		return goalsForAway;
	}

	public void setGoalsForAway(int goalsForAway) {
		this.goalsForAway = goalsForAway;
	}

	public int getGoalsForTotal() {
		return goalsForTotal;
	}

	public void setGoalsForTotal(int goalsForTotal) {
		this.goalsForTotal = goalsForTotal;
	}

	public int getGoalsAgainstHome() {
		return goalsAgainstHome;
	}

	public void setGoalsAgainstHome(int goalsAgainstHome) {
		this.goalsAgainstHome = goalsAgainstHome;
	}

	public int getGoalsAgainstAway() {
		return goalsAgainstAway;
	}

	public void setGoalsAgainstAway(int goalsAgainstAway) {
		this.goalsAgainstAway = goalsAgainstAway;
	}

	public int getGoalsAgainstTotal() {
		return goalsAgainstTotal;
	}

	public void setGoalsAgainstTotal(int goalsAgainstTotal) {
		this.goalsAgainstTotal = goalsAgainstTotal;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
