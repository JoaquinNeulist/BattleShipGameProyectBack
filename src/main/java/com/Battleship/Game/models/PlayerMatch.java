package com.Battleship.Game.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayerMatch {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    public int score;

    private boolean turn;

    @Enumerated(EnumType.STRING)
    private PlayerStatus type;

    @OneToMany(mappedBy = "playerMatch")
    private List<Board> boards = new ArrayList<>();

    //Constructors
    public PlayerMatch() {
    }



    public PlayerMatch(PlayerStatus type) {
        this.type = type;
    }

    //Getters and setters
    public long getId() {
        return id;
    }

    public String getMail() {
        return account.getEmail();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public PlayerStatus getType() {
        return type;
    }

    public void setType(PlayerStatus type) {
        this.type = type;
    }

    public List<Board> getBoard() {
        return boards;
    }

    public void setBoard(List<Board> board) {
        this.boards = board;
    }

    public Account getAccount() {
        return account;
    }

    public void addBoard(Board board) {
        board.setPlayerMatch(this);
        boards.add(board);
    }

    //Methods
}
