package com.Battleship.Game.controllers;

import com.Battleship.Game.models.*;
import com.Battleship.Game.services.BoardService;
import com.Battleship.Game.services.GameService;
import com.Battleship.Game.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ShipService shipService;

    @Autowired
    private BoardService boardService;

    @PostMapping("/start")
    public ResponseEntity<?> createBoard(@RequestParam String configuration){
        Board board = boardService.createBoard(configuration);
        return ResponseEntity.ok(board);
    }

    @PostMapping("/initializeBoard")
    public ResponseEntity<?> initializeBoard(@RequestBody Board board, @RequestBody List<Ship> ships){
        Board updatedBoard = boardService.initializeBoard(board, ships);
        return ResponseEntity.ok(updatedBoard);
    }

    @PostMapping("/placeShip")
    public ResponseEntity<?> placeShip(@RequestBody Board board, @RequestBody Ship ship){
        Board updatedBoard = boardService.placeShip(board, ship);
        return ResponseEntity.ok(updatedBoard);
    }

    @PostMapping("/registerShoot")
    public ResponseEntity<?> registerShoot(@RequestBody Board board, @RequestParam int x, @RequestParam int y){
        Shoot newShoot = boardService.registerShoot(board, x, y);
        return ResponseEntity.ok(newShoot);
    }

    @PostMapping("/calculateShoot")
    public ResponseEntity<?> calculateShoot(@RequestBody Board board, @RequestParam int x, @RequestParam int y){
        ShootResult result = boardService.calculateShootResult(board, x, y);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/allShipsSunk")
    public ResponseEntity<?> allShipsSunk(@RequestBody Board board){
        boolean allShipsSunk = boardService.allShipsSunk(board);
        return ResponseEntity.ok(allShipsSunk);
    }

    @GetMapping("/boardStatus")
    public ResponseEntity<?> getBoardStatus(@RequestBody Board board){
        BoardStatus boardStatus = boardService.getBoardStatus(board);
        return ResponseEntity.ok(boardStatus);
    }

    @PostMapping("/end")
    public void endGame(@RequestBody GameEndRequest request){
        gameService.handleGameEnd(request.getWinnerId(), request.getLoserId());
    }
}
