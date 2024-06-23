package com.Battleship.Game.dtos;

import com.Battleship.Game.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private long id;

    private String email;

    private String fName;

    private String lName;

    private String username;

    private List<RankingDTO> rankingDTOS = new ArrayList<>();

    private List<PlayerMatchDTO> playerMatchDTOS = new ArrayList<>();

    public UserDTO(User user){
        this.id = user.getId();
        this.fName = user.getfName();
        this.lName = user.getlName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.playerMatchDTOS = user.getPlayersInMatch().stream().map(playerMatch -> new PlayerMatchDTO(playerMatch)).collect(Collectors.toList());
        this.rankingDTOS = user.getRankings().stream().map(ranking -> new RankingDTO(ranking)).collect(Collectors.toList());
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

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public List<RankingDTO> getRankingDTOS() {
        return rankingDTOS;
    }

    public List<PlayerMatchDTO> getPlayerMatchDTOS() {
        return playerMatchDTOS;
    }
}
