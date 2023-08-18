import React from "react";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  DialogActions,
  Button,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
} from "@mui/material";

const Modal = ({
  open,
  onClose,
  leagueInfo,
  teams,
  selectedSeason,
  selectedTeam,
  handleSeasonChange,
  handleTeamChange,
  handleSubmit,
}) => {
  //const years = Array.from({ length: 6 }, (_, index) => 2018 + index);
  return (
    <Dialog
      open={open}
      onClose={onClose}
      sx={{ width: "100%", minWidth: 200, marginLeft: 3 }}
    >
      <DialogTitle></DialogTitle>
      <DialogContent>
        <DialogContentText>
          <FormControl
            variant="outlined"
            sx={{ width: "100%", minWidth: 200, marginTop: 3 }}
          >
            <InputLabel>Select Team</InputLabel>
            <Select
              value={selectedTeam}
              onChange={handleTeamChange}
              label="Select Team"
            >
              {teams.map((team) => (
                <MenuItem key={team.teamId} value={team.teamId}>
                  {team.teamName}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={handleSubmit} color="primary">
          <b>Generate PDF</b>
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default Modal;
