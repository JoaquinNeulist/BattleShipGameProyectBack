package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Match;
import com.Battleship.Game.models.MatchState;

import java.time.LocalDateTime;

public class MatchDTO {
    private long id;

    private MatchState state;

    private LocalDateTime startTime;

    private LocalDateTime finalTime;

    public MatchDTO(Match match) {
        this.id= match.getId();
        this.state = match.getState();
        this.startTime = match.getStartTime();
        this.finalTime = match.getFinalTime();
    }

    public long getId() {
        return id;
    }

    public MatchState getState() {
        return state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getFinalTime() {
        return finalTime;
    }
}
