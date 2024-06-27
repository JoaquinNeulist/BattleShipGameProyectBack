package com.Battleship.Game.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int x;
    private int y;

    @ManyToOne
    private Ship ship;

    private boolean hit;

    public Coordinate() {}

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters y setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isHit() {
        return hit;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("{\"x\":%d,\"y\":%d}", x, y);
    }


    public boolean equals(Coordinate coordinate) {
        if (this == coordinate) return true;
        if (coordinate == null || getClass() != coordinate.getClass()) return false;
        return this.x == coordinate.x && this.y == coordinate.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
