package com.Battleship.Game.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String fName;

    private String lName;

    @Column(unique = true, nullable = false)
    private String username;

    public String password;

    @OneToMany
    private List<Ranking> rankings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerMatch> playersInMatch = new ArrayList<>();

    public User (){}

    public User (String email, String fName, String lName, String username, String password){
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<PlayerMatch> getPlayersInMatch() {
        return playersInMatch;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlayersInMatch(List<PlayerMatch> playersInMatch) {
        this.playersInMatch = playersInMatch;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    public void addPlayersInMatch(PlayerMatch playerMatchs){
        playerMatchs.setUserId(this);
        playersInMatch.add(playerMatchs);
    }

    public void addRankings(Ranking ranking){
        ranking.setUser(this);
        rankings.add(ranking);
    }
}
