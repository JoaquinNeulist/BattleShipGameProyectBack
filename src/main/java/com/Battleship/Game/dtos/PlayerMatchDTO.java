package com.Battleship.Game.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.Battleship.Game.models.PlayerMatch;
import  com.Battleship.Game.models.playerStatus;

public class PlayerMatchDTO {
    private long id;

    private LocalDateTime matchDuration;

    private boolean turn;

    private playerStatus type;

    private List<BoardDTO> boardDTOS = new ArrayList<>();

    public PlayerMatchDTO(PlayerMatch playerMatch){
        this.id = playerMatch.getId();
        this.matchDuration = playerMatch.getMatchDuration();
        this.turn = playerMatch.isTurn();
        this.type = playerMatch.getType();
        this.boardDTOS = playerMatch.getBoards().stream().map(board -> new BoardDTO(board)).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public boolean isTurn() {
        return turn;
    }

    public LocalDateTime getMatchDuration() {
        return matchDuration;
    }

    public playerStatus getType() {
        return type;
    }

    public List<BoardDTO> getBoardDTOS() {
        return boardDTOS;
    }
}
