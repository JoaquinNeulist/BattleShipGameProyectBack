package com.Battleship.Game.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayerMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    private LocalDateTime matchDuration;

    private boolean turn;

    private playerStatus type;

    @OneToMany
    private List<Board> boards = new ArrayList<>();

    public PlayerMatch() {
    }

    public PlayerMatch(Match match, LocalDateTime matchDuration, boolean turn, playerStatus type) {
        this.matchDuration = matchDuration;
        this.turn = turn;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public LocalDateTime getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(LocalDateTime matchDuration) {
        this.matchDuration = matchDuration;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public playerStatus getType() {
        return type;
    }

    public void setType(playerStatus type) {
        this.type = type;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public void addBoard(Board board){
        board.setPlayerMatch(this);
        boards.add(board);
    }
}
