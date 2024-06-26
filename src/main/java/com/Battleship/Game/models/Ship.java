package com.Battleship.Game.models;

import jakarta.persistence.*;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    private boolean isSideways;

    @Enumerated(EnumType.STRING)
    private ShipStatus status;

    private int size;

    @ManyToOne
    private Board board;

    @Column(columnDefinition = "json")
    private String coordinates;

    public Ship(){}

    public Ship(ShipType shipType, int size, boolean isSideways, ShipStatus status) {
        this.shipType = shipType;
        this.size = size;
        this.isSideways = isSideways;
        this.status = status;
    }

    public Ship(ShipType shipType, int size, boolean isSideways, ShipStatus status, String coordinates) {
        this.shipType = shipType;
        this.size = size;
        this.isSideways = isSideways;
        this.status = status;
        this.coordinates = coordinates;

    }

    public long getId() {
        return id;
    }

    public int getSize() {
        return size;
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setSize(int size) {
        this.size = size;
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
