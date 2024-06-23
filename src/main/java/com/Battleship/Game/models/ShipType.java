package com.Battleship.Game.models;

public enum ShipType {
    DESTROYER(1),
    SUBMARINE(2),
    CRUISER(3),
    BATTLESHIP(4);

    private final int size;

    ShipType(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
