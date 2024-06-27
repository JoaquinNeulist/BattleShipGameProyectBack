package com.Battleship.Game.controllers;

import com.Battleship.Game.dtos.ShootRequest;
import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.*;
import com.Battleship.Game.services.AccountService;
import com.Battleship.Game.services.BoardService;
import com.Battleship.Game.services.PlayerService;
import com.Battleship.Game.services.ShootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/shoot")
public class ShootController {

    @Autowired
    private ShootService shootService;
    @PostMapping("/{boardId}")
    public ResponseEntity<?> shoot(@PathVariable Long boardId, @RequestBody ShootRequest shootRequest, Authentication authentication) {
        return shootService.generateShoot(boardId, shootRequest, authentication);
    }
 }




