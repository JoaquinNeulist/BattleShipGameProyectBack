package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Ranking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RankingDTO {
    private long id;
    private long score;
    private long position;
    private List<AccountDTO> accountDTOS = new ArrayList<>();

    // Constructor con argumentos
    public RankingDTO(Ranking ranking) {
        this.id = ranking.getId();
        this.score = ranking.getScore();
        this.position = ranking.getPosition();
        this.accountDTOS = ranking.getUsers().stream().map(user -> new AccountDTO(user)).collect(Collectors.toList());
    }

    // Getters
    public long getId() {
        return id;
    }

    public long getScore() {
        return score;
    }

    public long getPosition() {
        return position;
    }

}
