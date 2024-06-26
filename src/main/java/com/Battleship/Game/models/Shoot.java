package com.Battleship.Game.models;

import jakarta.persistence.*;

@Entity
public class Shoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "json")
    private String coordinates;

    private int cordX;

    private int cordY;

    @Enumerated(EnumType.STRING)
    private ShootResult result;

    @ManyToOne
    private Board board;

    public Shoot () {}

    public Shoot (Coordinate coordinate){
        this.cordX = coordinate.getX();
        this.cordY = coordinate.getY();
    }

    public Shoot (int cordX, int cordY, ShootResult result){
        this.cordX = cordX;
        this.cordY = cordY;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public ShootResult getResult() {
        return result;
    }

    public Board getBoard() {
        return board;
    }

    public void setCordX(int cordX) {
        this.cordX = cordX;
    }

    public void setCordY(int cordY) {
        this.cordY = cordY;
    }

    public void setResult(ShootResult result) {
        this.result = result;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

