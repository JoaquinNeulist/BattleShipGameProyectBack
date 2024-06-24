package com.Battleship.Game.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private boolean isAdmin;

    private String fName;

    private String lName;

    @Column(unique = true, nullable = false)
    private String username;

    public String password;

    @OneToMany(mappedBy = "account")
    private List<PlayerMatch> playersInMatch = new ArrayList<>();

    @ManyToOne
    private Ranking ranking;

    public Account(){}

    public Account(String email, String fName, String lName, String username, String password) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.isAdmin = false; //por defecto el rol de usuario es USER
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

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public List<PlayerMatch> getPlayersInMatch() {
        return playersInMatch;
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

    public void addPlayersInMatch(PlayerMatch playerMatchs){
        playerMatchs.setUserId(this);
        playersInMatch.add(playerMatchs);
    }

    public Ranking getRanking() {
            return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rankings=" + ranking +
                ", playersInMatch=" + playersInMatch +
                '}';
    }
}
