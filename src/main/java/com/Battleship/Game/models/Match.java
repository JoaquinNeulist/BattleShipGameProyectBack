package com.Battleship.Game.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
public class Match {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private MatchState state;

    private LocalDateTime startTime;

    private LocalDateTime finalTime;

    @Column(unique = true, nullable = false)
    private String partyCode;

    @OneToMany(mappedBy = "match")
    private List<PlayerMatch> playerMatches = new ArrayList<>();

    //Constructors
    public Match() {
    }

    public Match(MatchState state) {
        this.state = state;
        this.partyCode = generateUniqueCode();
    }

    public Match(MatchState state, LocalDateTime startTime, LocalDateTime finalTime) {
        this.state = state;
        this.partyCode = generateUniqueCode();
        this.startTime = startTime;
        this.finalTime = startTime.plusMinutes(30);
    }

    //Getters and setters
    public long getId() {
        return id;
    }

    public MatchState getState() {
        return state;
    }

    public String getPartyCode() { return partyCode; }

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

    //Methods
    public void addPlayersMatch(PlayerMatch playerMatch) {
        playerMatch.setMatch(this);
        playerMatches.add(playerMatch);
    }

    public String generateUniqueCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
