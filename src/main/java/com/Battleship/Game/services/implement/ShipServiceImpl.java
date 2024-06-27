package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.Ship;
import com.Battleship.Game.models.ShipType;
import com.Battleship.Game.models.ShipStatus;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;


    @Override
    public ResponseEntity<?> knowAllShips(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
        List<Ship> ships = generateShipsTemporary();
        return ResponseEntity.ok(ships);
    }

    @Override
    public void saveShip(Ship ship) {
        shipRepository.save(ship);
    }

    private List<Ship> generateShipsTemporary(){
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(ShipType.BATTLESHIP, 4, ShipStatus.INTACT));
        ships.add(new Ship(ShipType.CRUISER, 2, ShipStatus.INTACT));
        ships.add(new Ship(ShipType.DESTROYER, 1, ShipStatus.INTACT));
        ships.add(new Ship(ShipType.SUBMARINE, 3, ShipStatus.INTACT));
        return ships;

    }

}