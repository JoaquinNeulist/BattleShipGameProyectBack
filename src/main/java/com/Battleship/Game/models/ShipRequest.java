package com.Battleship.Game.models;

import java.util.ArrayList;
import java.util.List;

public class ShipRequest {

    private String type;

    private List<Coordinate> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
