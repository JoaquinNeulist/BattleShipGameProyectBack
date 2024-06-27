package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Match;
import com.Battleship.Game.models.MatchState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MatchDTO {
    private long id;

    private MatchState state;

    private String partyCode;

    private LocalDateTime startTime;

    private LocalDateTime finalTime;

    private List<PlayerMatchDTO> playerMatches;

    public MatchDTO(Match match) {
        this.id= match.getId();
        this.partyCode = match.getPartyCode();
        this.state = match.getState();
        this.startTime = match.getStartTime();
        this.finalTime = match.getFinalTime();
        this.playerMatches = match.getPlayerMatches()
                .stream()
                .map(playerMatch -> new PlayerMatchDTO(playerMatch))
                .collect(Collectors.toList());

    }

    public long getId() {
        return id;
    }

    public MatchState getState() {
        return state;
    }

    public List<PlayerMatchDTO> getPlayerMatches() {
        return playerMatches;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getFinalTime() {
        return finalTime;
    }

    public String getPartyCode() {
        return partyCode;
    }
}
