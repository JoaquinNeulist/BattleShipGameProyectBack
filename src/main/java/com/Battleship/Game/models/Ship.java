package com.Battleship.Game.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Enumerated(EnumType.STRING)
    private ShipStatus status;

    private int size;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "ship")
    private List<Coordinate> coordinates = new ArrayList<>();

    public Ship(){}

    public Ship(ShipType shipType, int size, ShipStatus status) {
        this.shipType = shipType;
        this.size = size;
        this.status = status;
    }

    public Ship(ShipType shipType, int size, ShipStatus status, List<Coordinate> coordinates) {
        this.shipType = shipType;
        this.size = size;
        this.status = status;
        this.coordinates = coordinates;

    }

    public long getId() {
        return id;
    }

    public int getSize() {
        return size;
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

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addCoordinate(Coordinate newCoordinates) {
            newCoordinates.setShip(this);
            coordinates.add(newCoordinates);
    }
}


