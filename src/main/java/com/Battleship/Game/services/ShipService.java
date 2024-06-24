package com.Battleship.Game.services;

import com.Battleship.Game.models.Ship;
import com.Battleship.Game.models.ShipType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShipService {

    Ship createShip(ShipType type, int size, boolean isSideways);

    boolean placeShip(Long shipId, int newX, int newY);

    void damageShip(Long shipId); // Para registrar daño en un barco

    void sinkShip(Long shipId); // Para hundir un barco

    Ship getShipById(Long shipId);
}
