package com.Battleship.Game.models;

import jakarta.persistence.*;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ShipType shipType;

    private boolean isSideways;

    private ShipStatus status;

   @ManyToOne
   private Board board;

    public Ship(){}

    public Ship(ShipType shipType, boolean isSideways, ShipStatus status){
        this.shipType = shipType;
        this.isSideways = isSideways;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public boolean isSideways() {
        return isSideways;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }


    public void setSideways(boolean sideways) {
        isSideways = sideways;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
