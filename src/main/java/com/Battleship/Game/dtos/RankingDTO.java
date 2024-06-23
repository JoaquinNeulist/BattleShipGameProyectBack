package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Ranking;

public class RankingDTO {
    private long id;
    private String username;
    private long score;
    private long position;

    // Constructor con argumentos
    public RankingDTO(Ranking ranking) {
        this.id = ranking.getId();
        this.username = ranking.getUsername();
        this.score = ranking.getScore();
        this.position = ranking.getPosition();
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public long getScore() {
        return score;
    }

    public long getPosition() {
        return position;
    }

}
