package com.Battleship.Game.controllers;

import com.Battleship.Game.dtos.ShootRequest;
import com.Battleship.Game.models.Board;
import com.Battleship.Game.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shoot")
public class ShootController {

    @Autowired
    private BoardRepository boardRepository;

    @PostMapping("/{boardId}")
    public ResponseEntity<?> shoot(@PathVariable Long boardId, @RequestBody ShootRequest shootRequest, Authentication authentication) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board == null) {
            return new ResponseEntity<>("Board not found", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(shootRequest, HttpStatus.OK);
    }
}
