package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Ship;
import com.Battleship.Game.models.ShipStatus;
import com.Battleship.Game.models.ShipType;

public class ShipDTO {
    private long id;

    private boolean isSideways;

    private ShipStatus status;

    private ShipType type;

    // Constructor con argumentos
    public ShipDTO(Ship ship) {
        this.id = ship.getId();
        this.isSideways = ship.isSideways();
        this.type = ship.getShipType();
        this.status = ship.getStatus();
    }

    // Getters

    public long getId() {
        return id;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public ShipType getType() {
        return type;
    }

    public boolean isSideways() {
        return isSideways;
    }
}
