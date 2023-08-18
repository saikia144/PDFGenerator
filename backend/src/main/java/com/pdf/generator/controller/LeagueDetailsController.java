package com.pdf.generator.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdf.generator.utils.URIBuilderUtil;


//http://localhost:8081/api/get-league-details?league=323&season=2019
//@CrossOrigin(origins="https://pdfgene.netlify.app")
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LeagueDetailsController {
	@Value("${api.key}")
	private String apiKey;
	
	@GetMapping("/get-league-details")
	public ResponseEntity<Map<String,Object>> getLeagueDetails(@RequestParam String league, @RequestParam String season){
		System.out.println("Inside LeagueDetails method");
		Map<String, Object> leagueDetails = fetchLeagueDetailsFromAPI(league,season);
		return ResponseEntity.ok(leagueDetails);
	}
	
	private Map<String, Object> fetchLeagueDetailsFromAPI(String leagueId, String season){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		Map<String, Object> teamMap = new HashMap<>();
		try {
			URI uri = URIBuilderUtil.buildURI("https://v3.football.api-sports.io/teams",season,leagueId);
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("x-rapidapi-key", apiKey);
			
			CloseableHttpResponse response = httpClient.execute(httpGet);
			String responseBody = EntityUtils.toString(response.getEntity());
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(responseBody).get("response");
			
			for(JsonNode teamNode : rootNode) {
				String id = teamNode.get("team").get("id").asText();
				String name = teamNode.get("team").get("name").asText();
				teamMap.put(name, id);
			}
			System.out.println(teamMap);
			//System.out.println(teamMap.size());
			
		} catch (URISyntaxException | ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teamMap;
	}
}
