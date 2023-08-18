import React, { useEffect, useState } from "react";
import {
  CircularProgress,
  Card,
  CardContent,
  Typography,
  FormControl, // Import FormControl
  InputLabel, // Import InputLabel
  Select, // Import Select
  MenuItem, // Import MenuItem
} from "@mui/material";
import axios from "axios";
import Modal from "./Modal";
import { saveAs } from "file-saver";

const years = Array.from({ length: 5 }, (_, index) => 2018 + index);

export default function League() {
  const [leaguesData, setLeaguesData] = useState([]);
  const [selectedSeason, setSelectedSeason] = useState(""); // State for selected season (year)
  const desiredClubNames = ["Indian Super League"];
  const [isModalOpen, setIsModalOpen] = useState(false); // State for modal visibility
  const [selectedLeagueId, setSelectedLeagueId] = useState(null); // State for selected league ID
  const [leagueDetails, setLeagueDetails] = useState(null);
  const [teams, setTeams] = useState([]); // State for storing teams data
  const [selectedTeam, setSelectedTeam] = useState(""); // State for selected team ID
  const [isLoading, setIsLoading] = useState(false);
  const [isGeneratingPDF, setIsGeneratingPDF] = useState(false);

  const filteredLeagues = leaguesData.filter((league) =>
    desiredClubNames.includes(league.league.name)
  );

  const handleLeagueClick = (leagueId) => {
    setSelectedLeagueId(leagueId); // Store the selected league ID
    setIsModalOpen(true); // Open the modal
    //pivotal-sprite-394013.el.r.appspot.com
    axios
      .get(
        `http://localhost:8081/api/get-league-details?league=${leagueId}&season=${selectedSeason}`
      )
      .then((response) => {
        const leagueDetails = response.data;
        const teamsArray = Object.entries(leagueDetails).map(
          ([teamName, teamId]) => ({
            teamName,
            teamId,
          })
        );
        setLeagueDetails(leagueDetails);
        setTeams(teamsArray); // Converted the teamMap to an array of objects and stored it in the state

        console.log(leagueDetails);
      })
      .catch((error) => console.log(error));
  };

  const handleSubmit = () => {
    //pivotal-sprite-394013.el.r.appspot.com

    setIsGeneratingPDF(true);
    setIsModalOpen(false);
    setIsLoading(true);
    axios
      .get(
        `http://localhost:8081/player/details?league=${selectedLeagueId}&season=${selectedSeason}&team=${selectedTeam}`,
        { responseType: "blob" }
      )
      .then((response) => {
        console.log(response);
        const pdfBlob = new Blob([response.data], { type: "application/pdf" });
        saveAs(pdfBlob, "stats.pdf");
        setIsModalOpen(false); // Close
        setIsGeneratingPDF(false);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        setIsLoading(false);
      });
  };

  useEffect(() => {
    fetch("https://v3.football.api-sports.io/leagues", {
      method: "GET",
      headers: {
        "x-rapidapi-host": "v3.football.api-sports.io",
        "x-rapidapi-key": "5d9298fee11597666a5aa6a5cca54cc9",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setLeaguesData(data.response); // Set leaguesData to the array within the response property
      })
      .catch((error) => console.log(error));
  }, []);

  return (
    <center>
      <Card
        sx={{
          minWidth: 500,
          maxWidth: 300,
          borderRadius: 5,
          marginTop: 8,
          marginLeft: 6,
        }}
      >
        <CardContent>
          <Typography
            sx={{ fontSize: 20, fontWeight: "bold" }}
            color="black"
            gutterBottom
          >
            Leagues
          </Typography>
          <FormControl variant="outlined" sx={{ width: "50%", minWidth: 200 }}>
            <InputLabel>Select Season</InputLabel>
            <Select
              value={selectedSeason}
              onChange={(event) => setSelectedSeason(event.target.value)}
              label="Select Season"
            >
              {years.map((year) => (
                <MenuItem key={year} value={year}>
                  {year}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <hr />
          {leaguesData.length > 0 ? (
            filteredLeagues.map((league) => (
              <Typography
                key={league.league.id}
                color="text.secondary"
                style={{
                  cursor: "pointer",
                }}
              >
                <a href="#" onClick={() => handleLeagueClick(league.league.id)}>
                  {league.league.name}
                </a>
              </Typography>
            ))
          ) : (
            <Typography color="text.secondary">No leagues found</Typography>
          )}
          {isGeneratingPDF && (
            <Typography
              variant="subtitle1"
              color="success"
              fontWeight="bold"
              style={{ marginTop: "1rem" }}
            >
              Please wait while your PDF is getting generated!!! It may take a
              while!!
            </Typography>
          )}
        </CardContent>
      </Card>
      {leagueDetails && (
        <Modal
          open={isModalOpen}
          onClose={() => setIsModalOpen(false)} // Close modal
          leagueInfo={leagueDetails}
          teams={teams}
          selectedSeason={selectedSeason}
          selectedTeam={selectedTeam}
          handleSeasonChange={(event) => setSelectedSeason(event.target.value)}
          handleTeamChange={(event) => setSelectedTeam(event.target.value)}
          handleSubmit={handleSubmit}
        />
      )}
      <br />
      {isLoading && <CircularProgress />}
    </center>
  );
}
