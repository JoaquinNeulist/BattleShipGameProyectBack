package com.Battleship.Game;

import com.Battleship.Game.models.Ship;
import com.Battleship.Game.models.ShipStatus;
import com.Battleship.Game.models.ShipType;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ShipService shipService;

    @Override
    public void run(String... args) throws Exception {
        if (shipService.getAllShips().isEmpty()) {
            shipService.saveShip(new Ship(ShipType.BATTLESHIP, 4, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.CRUISER, 3, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.CRUISER, 3, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.SUBMARINE, 2, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.SUBMARINE, 2, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.SUBMARINE, 2, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.DESTROYER, 1, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.DESTROYER, 1, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.DESTROYER, 1, false, ShipStatus.INTACT));
            shipService.saveShip(new Ship(ShipType.DESTROYER, 1, false, ShipStatus.INTACT));
        }
    }
}
