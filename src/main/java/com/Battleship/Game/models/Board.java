package com.Battleship.Game.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String configuration;

    @ManyToOne
    private PlayerMatch playerMatch;

    @OneToMany
    private List<Ship> ships = new ArrayList<>();

    @OneToMany
    private List<Shoot> shoots = new ArrayList<>();

    public Board (){}

    public Board (String configuration){
        this.configuration = configuration;
    }

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

    public void addShoot(Shoot shoot){
        shoot.setBoard(this);
        shoots.add(shoot);
    }

    public void addShip(Ship ship){
        ship.setBoard(this);
        ships.add(ship);
    }

}
