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

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return String.format("{\"x\":%d,\"y\":%d}", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
