package com.Battleship.Game.controllers;

import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.BoardRepository;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.services.BoardService;
import com.Battleship.Game.services.ShipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private ShipRepository shipRepository;

    @PostMapping("/{boardId}/ships")
    public ResponseEntity<?> addShipsToBoard(@PathVariable Long boardId, @RequestBody BoardRequest boardRequest, Authentication authentication) {
        return boardService.createBoard(boardId, boardRequest, authentication);
    }
}

