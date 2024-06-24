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

    private List<PlayerMatchDTO> playerMatchDTOS = new ArrayList<>();

    public AccountDTO(Account account){
        this.id = account.getId();
        this.fName = account.getfName();
        this.lName = account.getlName();
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.playerMatchDTOS = account.getPlayersInMatch().stream().map(playerMatch -> new PlayerMatchDTO(playerMatch)).collect(Collectors.toList());
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

    public List<PlayerMatchDTO> getPlayerMatch() {
        return playerMatchDTOS;
    }
}
