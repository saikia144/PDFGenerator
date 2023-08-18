package com.pdf.generator.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class URIBuilderUtil {
	public static URI buildURI(String endpoint, String... param) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(endpoint);
		if(endpoint.equals("https://v3.football.api-sports.io/players")) {
			uriBuilder.addParameter("team", param[0]);
			uriBuilder.addParameter("season", param[1]);
            uriBuilder.addParameter("league", param[2]);
		}else if(endpoint.equals("https://v3.football.api-sports.io/teams/statistics")){
			uriBuilder.addParameter("team", param[0]);
			uriBuilder.addParameter("season", param[1]);
            uriBuilder.addParameter("league", param[2]);
		}else {
			System.out.println(param[0]+" "+param[1]);
			uriBuilder.addParameter("season", param[0]);
			uriBuilder.addParameter("league", param[1]);
		}
		return uriBuilder.build();
	}
}
