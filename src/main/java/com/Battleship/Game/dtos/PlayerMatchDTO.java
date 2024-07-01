package com.Battleship.Game.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.Battleship.Game.models.PlayerMatch;
import com.Battleship.Game.models.PlayerStatus;

public class PlayerMatchDTO {
    private long id;

    private boolean turn;

    private PlayerStatus type;

    private List<BoardDTO> boards;

    private String username;

    private AccountDTO2 account;

    public PlayerMatchDTO(PlayerMatch playerMatch){
        this.id = playerMatch.getId();
        this.account = new AccountDTO2(playerMatch.getAccount());
        this.turn = playerMatch.isTurn();
        this.type = playerMatch.getType();
        this.boards = playerMatch.getBoard().stream().map(board -> new BoardDTO(board)).collect(Collectors.toList());
        this.username = playerMatch.getAccount().getUsername();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<BoardDTO> getBoards() {
        return boards;
    }

    public AccountDTO2 getAccount() {
        return account;
    }

    public boolean isTurn() {
        return turn;
    }

    public PlayerStatus getType() {
        return type;
    }

}
