package com.Battleship.Game.models;

import java.util.List;

public class BoardStatus {

    //Attributes
    private List<Ship> ships;
    private List<Shoot> shoots;

    //Constructors
    public BoardStatus(){}

    public BoardStatus(List<Ship> ships, List<Shoot> shoots) {
        this.ships = ships;
        this.shoots = shoots;
    }

    //Getters and setters
    public List<Shoot> getShoots() {
        return shoots;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShoots(List<Shoot> shoots) {
        this.shoots = shoots;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    //Methods
    public int getRemainingShips() {
        int remaining = 0;
        for (Ship ship : ships) {
            if (ship.getStatus() != ShipStatus.SUNK) {
                remaining++;
            }
        }
        return remaining;
    }

    public int getTotalShots() {
        return shoots.size();
    }

    public int getHits() {
        int hits = 0;
        for (Shoot shoot : shoots) {
            if (shoot.getResult() == ShootResult.HIT) {
                hits++;
            }
        }
        return hits;
    }

    public int getMisses() {
        int misses = 0;
        for (Shoot shoot : shoots) {
            if (shoot.getResult() == ShootResult.MISS) {
                misses++;
            }
        }
        return misses;
    }
}

