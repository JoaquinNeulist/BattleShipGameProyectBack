package com.Battleship.Game.controllers;

import com.Battleship.Game.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping("/ships")
    public ResponseEntity<?> knowAllShips(Authentication authentication) {
        return shipService.knowAllShips(authentication);
    }
}
