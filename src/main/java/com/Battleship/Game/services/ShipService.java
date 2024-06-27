package com.Battleship.Game.services;

import com.Battleship.Game.models.Ship;
import com.Battleship.Game.models.ShipType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShipService {

    ResponseEntity<?> knowAllShips(Authentication authentication);

    void saveShip(Ship ship);


}
