package com.pdf.generator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.pdf.generator.model.Team;

public interface TeamService {
	public Team saveTeam(JsonNode teams,String tid,String season, String league);	
	
}
