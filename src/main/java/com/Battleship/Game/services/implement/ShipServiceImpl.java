package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.Ship;
import com.Battleship.Game.models.ShipType;
import com.Battleship.Game.models.ShipStatus;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public void damageShip(Long shipId) {
        Ship ship = shipRepository.findById(shipId).orElse(null);
        if (ship != null) {
            ship.setStatus(ShipStatus.HIT);
            shipRepository.save(ship);
        }
    }

    @Override
    public void sinkShip(Long shipId) {
        Ship ship = shipRepository.findById(shipId).orElse(null);
        if (ship != null) {
            ship.setStatus(ShipStatus.SUNK);
            shipRepository.save(ship);
        }
    }

    @Override
    public Ship getShipById(Long shipId) {
        return shipRepository.findById(shipId).orElse(null);
    }

    @Override
    public void saveShip(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }
}