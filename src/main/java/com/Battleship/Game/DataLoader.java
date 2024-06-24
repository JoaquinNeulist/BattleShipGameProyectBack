package com.Battleship.Game;

import com.Battleship.Game.models.Ship;
import com.Battleship.Game.models.ShipStatus;
import com.Battleship.Game.models.ShipType;
import com.Battleship.Game.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public void run(String... args) throws Exception {
        if (shipRepository.count() == 0) {
            shipRepository.save(new Ship(ShipType.BATTLESHIP, 4, false, ShipStatus.INTACT, 0, 0));
            shipRepository.save(new Ship(ShipType.CRUISER, 3, false, ShipStatus.INTACT, 0,0));
            shipRepository.save(new Ship(ShipType.CRUISER, 3, false, ShipStatus.INTACT, 0, 0));
            shipRepository.save(new Ship(ShipType.SUBMARINE, 2, false, ShipStatus.INTACT, 0, 0));
            shipRepository.save(new Ship(ShipType.SUBMARINE, 2, false, ShipStatus.INTACT, 0, 0));
            shipRepository.save(new Ship(ShipType.SUBMARINE, 2, false, ShipStatus.INTACT, 0, 0));
            shipRepository.save(new Ship(ShipType.DESTROYER, 1, false, ShipStatus.INTACT, 0, 0));
            shipRepository.save(new Ship(ShipType.DESTROYER, 1, false, ShipStatus.INTACT, 0, 0));
            shipRepository.save(new Ship(ShipType.DESTROYER, 1, false, ShipStatus.INTACT, 0, 0));
            shipRepository.save(new Ship(ShipType.DESTROYER, 1, false, ShipStatus.INTACT, 0, 0));
        }
    }
}
