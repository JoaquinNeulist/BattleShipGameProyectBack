package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {
    private long id;

    private String email;

    private String fName;

    private String lName;

    private String username;

    private boolean isAdmin;

    private List<PlayerMatchDTO> playerMatchDTOS = new ArrayList<>();

    private int score;

    public AccountDTO(Account account){
        this.id = account.getId();
        this.fName = account.getfName();
        this.lName = account.getlName();
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.isAdmin = account.isAdmin();
        this.playerMatchDTOS = account.getPlayersInMatch().stream().map(playerMatch -> new PlayerMatchDTO(playerMatch)).collect(Collectors.toList());
        this.score = account.getScore();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() { return lName; }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public List<PlayerMatchDTO> getPlayerMatch() {
        return playerMatchDTOS;
    }

    public int getScore() {
    	return score;
    }
}
