package com.Battleship.Game.models;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private MatchState state;

    private LocalDateTime startTime;

    private LocalDateTime finalTime;

    @OneToMany
    private List<PlayerMatch> playerMatches = new ArrayList<>();

    public Match() {

    }
    public Match(MatchState state, LocalDateTime startTime, LocalDateTime finalTime) {
        this.state = state;
        this.startTime = startTime;
        this.finalTime = startTime.plusMinutes(30);
    }


    public long getId() {
        return id;
    }

    public MatchState getState() {
        return state;
    }

    public void setState(MatchState state) {
        this.state = state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(LocalDateTime finalTime) {
        this.finalTime = finalTime;
    }

    public List<PlayerMatch> getPlayerMatches() {
        return playerMatches;
    }

    public void setPlayerMatches(List<PlayerMatch> playerMatches) {
        this.playerMatches = playerMatches;
    }

    public void addPlayersMatch(PlayerMatch playerMatch){
        playerMatch.setMatch(this);
        playerMatches.add(playerMatch);
    }
}
