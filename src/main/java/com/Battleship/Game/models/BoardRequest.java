package com.Battleship.Game.models;

import java.util.ArrayList;
import java.util.List;

public class BoardRequest {

    private List<ShipRequest> ships = new ArrayList<>();

    public List<ShipRequest> getShips() {
        return ships;
    }

    public void setShips(List<ShipRequest> ships) {
        this.ships = ships;
    }
}
