package com.pdf.generator.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdf.generator.model.Team;
import com.pdf.generator.model.TeamStatistics;
import com.pdf.generator.utils.URIBuilderUtil;

@Service
public class TeamStatisticsServiceImpl implements TeamStatisticsService {
	@Value("${api.key}")
	private String apiKey;
	
	@Override
	public TeamStatistics fetchAndSaveTeamStatistics(Team t, String team,String season,String league) {
		// TODO Auto-generated method stub
		TeamStatistics ts = new TeamStatistics();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			URI uri = URIBuilderUtil.buildURI("https://v3.football.api-sports.io/teams/statistics",team,season,league);
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("x-rapidapi-key", apiKey);

			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			String responseBody = EntityUtils.toString(httpResponse.getEntity());
			//String responseBody = respo();
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode response = objectMapper.readTree(responseBody).get("response");
			
			 ts.setTeam(t);
			 ts.setPlayedHome(response.get("fixtures").get("played").get("home").asInt());
			 ts.setPlayedAway(response.get("fixtures").get("played").get("away").asInt());
			 ts.setPlayedTotal(response.get("fixtures").get("played").get("total").asInt());
			 
			 ts.setWinsHome(response.get("fixtures").get("wins").get("home").asInt());
			 ts.setWinsAway(response.get("fixtures").get("wins").get("away").asInt());
			 ts.setWinsTotal(response.get("fixtures").get("wins").get("total").asInt());
			 
			 ts.setDrawsHome(response.get("fixtures").get("draws").get("home").asInt());
			 ts.setDrawsAway(response.get("fixtures").get("draws").get("away").asInt());
			 ts.setDrawsTotal(response.get("fixtures").get("draws").get("total").asInt());
			 
			 ts.setLosesHome(response.get("fixtures").get("loses").get("home").asInt());
			 ts.setLosesAway(response.get("fixtures").get("loses").get("away").asInt());
			 ts.setLosesTotal(response.get("fixtures").get("loses").get("total").asInt());
			 
			 ts.setGoalsForHome(response.get("goals").get("for").get("total").get("home").asInt());
			 ts.setGoalsForAway(response.get("goals").get("for").get("total").get("home").asInt());
			 ts.setGoalsForTotal(response.get("goals").get("for").get("total").get("home").asInt());
			 
			 ts.setGoalsAgainstHome(response.get("goals").get("against").get("total").get("home").asInt());
			 ts.setGoalsAgainstAway(response.get("goals").get("against").get("total").get("away").asInt());
			 ts.setGoalsAgainstTotal(response.get("goals").get("against").get("total").get("total").asInt());
			 
			 System.out.println(response.get("fixtures"));
			 System.out.println(ts.getPlayedHome());
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ts;
	}
	
	public List<TeamStatistics> generateTeamStatsList(TeamStatistics ts){
		List<TeamStatistics> list = new ArrayList<>();
		list.add(ts);
		return list;
	}
	
	public String respo() {
		return "{\r\n"
				+ "    \"get\": \"teams/statistics\",\r\n"
				+ "    \"parameters\": {\r\n"
				+ "        \"league\": \"323\",\r\n"
				+ "        \"season\": \"2019\",\r\n"
				+ "        \"team\": \"3479\"\r\n"
				+ "    },\r\n"
				+ "    \"errors\": [],\r\n"
				+ "    \"results\": 11,\r\n"
				+ "    \"paging\": {\r\n"
				+ "        \"current\": 1,\r\n"
				+ "        \"total\": 1\r\n"
				+ "    },\r\n"
				+ "    \"response\": {\r\n"
				+ "        \"league\": {\r\n"
				+ "            \"id\": 323,\r\n"
				+ "            \"name\": \"Indian Super League\",\r\n"
				+ "            \"country\": \"India\",\r\n"
				+ "            \"logo\": \"https://media-1.api-sports.io/football/leagues/323.png\",\r\n"
				+ "            \"flag\": \"https://media-1.api-sports.io/flags/in.svg\",\r\n"
				+ "            \"season\": 2019\r\n"
				+ "        },\r\n"
				+ "        \"team\": {\r\n"
				+ "            \"id\": 3479,\r\n"
				+ "            \"name\": \"NorthEast United\",\r\n"
				+ "            \"logo\": \"https://media-2.api-sports.io/football/teams/3479.png\"\r\n"
				+ "        },\r\n"
				+ "        \"form\": \"DWDWDDLLDLLLLDDLLD\",\r\n"
				+ "        \"fixtures\": {\r\n"
				+ "            \"played\": {\r\n"
				+ "                \"home\": 9,\r\n"
				+ "                \"away\": 9,\r\n"
				+ "                \"total\": 18\r\n"
				+ "            },\r\n"
				+ "            \"wins\": {\r\n"
				+ "                \"home\": 1,\r\n"
				+ "                \"away\": 1,\r\n"
				+ "                \"total\": 2\r\n"
				+ "            },\r\n"
				+ "            \"draws\": {\r\n"
				+ "                \"home\": 5,\r\n"
				+ "                \"away\": 3,\r\n"
				+ "                \"total\": 8\r\n"
				+ "            },\r\n"
				+ "            \"loses\": {\r\n"
				+ "                \"home\": 3,\r\n"
				+ "                \"away\": 5,\r\n"
				+ "                \"total\": 8\r\n"
				+ "            }\r\n"
				+ "        },\r\n"
				+ "        \"goals\": {\r\n"
				+ "            \"for\": {\r\n"
				+ "                \"total\": {\r\n"
				+ "                    \"home\": 12,\r\n"
				+ "                    \"away\": 4,\r\n"
				+ "                    \"total\": 16\r\n"
				+ "                },\r\n"
				+ "                \"average\": {\r\n"
				+ "                    \"home\": \"1.3\",\r\n"
				+ "                    \"away\": \"0.4\",\r\n"
				+ "                    \"total\": \"0.9\"\r\n"
				+ "                },\r\n"
				+ "                \"minute\": {\r\n"
				+ "                    \"0-15\": {\r\n"
				+ "                        \"total\": 3,\r\n"
				+ "                        \"percentage\": \"17.65%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"16-30\": {\r\n"
				+ "                        \"total\": 1,\r\n"
				+ "                        \"percentage\": \"5.88%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"31-45\": {\r\n"
				+ "                        \"total\": 3,\r\n"
				+ "                        \"percentage\": \"17.65%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"46-60\": {\r\n"
				+ "                        \"total\": 2,\r\n"
				+ "                        \"percentage\": \"11.76%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"61-75\": {\r\n"
				+ "                        \"total\": 3,\r\n"
				+ "                        \"percentage\": \"17.65%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"76-90\": {\r\n"
				+ "                        \"total\": 5,\r\n"
				+ "                        \"percentage\": \"29.41%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"91-105\": {\r\n"
				+ "                        \"total\": null,\r\n"
				+ "                        \"percentage\": null\r\n"
				+ "                    },\r\n"
				+ "                    \"106-120\": {\r\n"
				+ "                        \"total\": null,\r\n"
				+ "                        \"percentage\": null\r\n"
				+ "                    }\r\n"
				+ "                }\r\n"
				+ "            },\r\n"
				+ "            \"against\": {\r\n"
				+ "                \"total\": {\r\n"
				+ "                    \"home\": 20,\r\n"
				+ "                    \"away\": 10,\r\n"
				+ "                    \"total\": 30\r\n"
				+ "                },\r\n"
				+ "                \"average\": {\r\n"
				+ "                    \"home\": \"2.2\",\r\n"
				+ "                    \"away\": \"1.1\",\r\n"
				+ "                    \"total\": \"1.7\"\r\n"
				+ "                },\r\n"
				+ "                \"minute\": {\r\n"
				+ "                    \"0-15\": {\r\n"
				+ "                        \"total\": 3,\r\n"
				+ "                        \"percentage\": \"10.34%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"16-30\": {\r\n"
				+ "                        \"total\": 3,\r\n"
				+ "                        \"percentage\": \"10.34%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"31-45\": {\r\n"
				+ "                        \"total\": 6,\r\n"
				+ "                        \"percentage\": \"20.69%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"46-60\": {\r\n"
				+ "                        \"total\": 5,\r\n"
				+ "                        \"percentage\": \"17.24%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"61-75\": {\r\n"
				+ "                        \"total\": 3,\r\n"
				+ "                        \"percentage\": \"10.34%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"76-90\": {\r\n"
				+ "                        \"total\": 5,\r\n"
				+ "                        \"percentage\": \"17.24%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"91-105\": {\r\n"
				+ "                        \"total\": 4,\r\n"
				+ "                        \"percentage\": \"13.79%\"\r\n"
				+ "                    },\r\n"
				+ "                    \"106-120\": {\r\n"
				+ "                        \"total\": null,\r\n"
				+ "                        \"percentage\": null\r\n"
				+ "                    }\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        },\r\n"
				+ "        \"biggest\": {\r\n"
				+ "            \"streak\": {\r\n"
				+ "                \"wins\": 1,\r\n"
				+ "                \"draws\": 2,\r\n"
				+ "                \"loses\": 4\r\n"
				+ "            },\r\n"
				+ "            \"wins\": {\r\n"
				+ "                \"home\": \"2-1\",\r\n"
				+ "                \"away\": \"0-1\"\r\n"
				+ "            },\r\n"
				+ "            \"loses\": {\r\n"
				+ "                \"home\": \"1-5\",\r\n"
				+ "                \"away\": \"2-0\"\r\n"
				+ "            },\r\n"
				+ "            \"goals\": {\r\n"
				+ "                \"for\": {\r\n"
				+ "                    \"home\": 3,\r\n"
				+ "                    \"away\": 1\r\n"
				+ "                },\r\n"
				+ "                \"against\": {\r\n"
				+ "                    \"home\": 5,\r\n"
				+ "                    \"away\": 2\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        },\r\n"
				+ "        \"clean_sheet\": {\r\n"
				+ "            \"home\": 1,\r\n"
				+ "            \"away\": 2,\r\n"
				+ "            \"total\": 3\r\n"
				+ "        },\r\n"
				+ "        \"failed_to_score\": {\r\n"
				+ "            \"home\": 3,\r\n"
				+ "            \"away\": 5,\r\n"
				+ "            \"total\": 8\r\n"
				+ "        },\r\n"
				+ "        \"penalty\": {\r\n"
				+ "            \"scored\": {\r\n"
				+ "                \"total\": 3,\r\n"
				+ "                \"percentage\": \"100.00%\"\r\n"
				+ "            },\r\n"
				+ "            \"missed\": {\r\n"
				+ "                \"total\": 0,\r\n"
				+ "                \"percentage\": \"0%\"\r\n"
				+ "            },\r\n"
				+ "            \"total\": 3\r\n"
				+ "        },\r\n"
				+ "        \"lineups\": [\r\n"
				+ "            {\r\n"
				+ "                \"formation\": \"4-2-3-1\",\r\n"
				+ "                \"played\": 10\r\n"
				+ "            },\r\n"
				+ "            {\r\n"
				+ "                \"formation\": \"4-1-4-1\",\r\n"
				+ "                \"played\": 6\r\n"
				+ "            },\r\n"
				+ "            {\r\n"
				+ "                \"formation\": \"4-3-3\",\r\n"
				+ "                \"played\": 2\r\n"
				+ "            }\r\n"
				+ "        ],\r\n"
				+ "        \"cards\": {\r\n"
				+ "            \"yellow\": {\r\n"
				+ "                \"0-15\": {\r\n"
				+ "                    \"total\": 1,\r\n"
				+ "                    \"percentage\": \"2.22%\"\r\n"
				+ "                },\r\n"
				+ "                \"16-30\": {\r\n"
				+ "                    \"total\": 3,\r\n"
				+ "                    \"percentage\": \"6.67%\"\r\n"
				+ "                },\r\n"
				+ "                \"31-45\": {\r\n"
				+ "                    \"total\": 11,\r\n"
				+ "                    \"percentage\": \"24.44%\"\r\n"
				+ "                },\r\n"
				+ "                \"46-60\": {\r\n"
				+ "                    \"total\": 5,\r\n"
				+ "                    \"percentage\": \"11.11%\"\r\n"
				+ "                },\r\n"
				+ "                \"61-75\": {\r\n"
				+ "                    \"total\": 5,\r\n"
				+ "                    \"percentage\": \"11.11%\"\r\n"
				+ "                },\r\n"
				+ "                \"76-90\": {\r\n"
				+ "                    \"total\": 20,\r\n"
				+ "                    \"percentage\": \"44.44%\"\r\n"
				+ "                },\r\n"
				+ "                \"91-105\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                },\r\n"
				+ "                \"106-120\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                }\r\n"
				+ "            },\r\n"
				+ "            \"red\": {\r\n"
				+ "                \"0-15\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                },\r\n"
				+ "                \"16-30\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                },\r\n"
				+ "                \"31-45\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                },\r\n"
				+ "                \"46-60\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                },\r\n"
				+ "                \"61-75\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                },\r\n"
				+ "                \"76-90\": {\r\n"
				+ "                    \"total\": 2,\r\n"
				+ "                    \"percentage\": \"100.00%\"\r\n"
				+ "                },\r\n"
				+ "                \"91-105\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                },\r\n"
				+ "                \"106-120\": {\r\n"
				+ "                    \"total\": null,\r\n"
				+ "                    \"percentage\": null\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}";
	}
}
