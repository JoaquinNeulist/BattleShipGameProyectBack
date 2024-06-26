package com.Battleship.Game.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String configuration;

    @ManyToOne
    private PlayerMatch playerMatch;

    @OneToMany(mappedBy = "board")
    private List<Ship> ships = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Shoot> shoots = new ArrayList<>();

   //Constructors
    public Board(String configuration, PlayerMatch playerMatch) {
        this.configuration = configuration;
        this.playerMatch = playerMatch;
    }

    public Board() {
    }

    public Board(String configuration) {
        this.configuration = configuration;
    }

    //Getters and setters
    public long getId() {
        return id;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Shoot> getShoots() {
        return shoots;
    }

    public PlayerMatch getPlayerMatch() {
        return playerMatch;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public void setPlayerMatch(PlayerMatch playerMatch) {
        this.playerMatch = playerMatch;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public void setShoots(List<Shoot> shoots) {
        this.shoots = shoots;
    }

    //Methods
    public void addShoot(Shoot shoot) {
        shoot.setBoard(this);
        shoots.add(shoot);
    }

    public void addShip(Ship ship) {
        ship.setBoard(this);
        ships.add(ship);
    }
}

