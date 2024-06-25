package com.Battleship.Game.dtos;

import com.Battleship.Game.models.GameEndRequest;

public class GameEndRequestDTO {

    private long winnerId;

    private long loserId;

    public GameEndRequestDTO(GameEndRequest gameEndRequest) {
        this.winnerId = gameEndRequest.getWinnerId();
        this.loserId = gameEndRequest.getLoserId();
    }

    public long getLoserId() {
        return loserId;
    }

    public long getWinnerId() {
        return winnerId;
    }
}
