package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO2 {
    private long id;

    private String username;

    private int score;

    public AccountDTO2(Account account){
        this.id = account.getId();
        this.username = account.getUsername();
        this.score = account.getScore();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public int getScore() {
        return score;
    }
}
