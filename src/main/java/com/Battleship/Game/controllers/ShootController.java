package com.Battleship.Game.controllers;

import com.Battleship.Game.dtos.ShootRequest;
import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.BoardRepository;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.repositories.ShootRepository;
import com.Battleship.Game.services.AccountService;
import com.Battleship.Game.services.BoardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shoot")
public class ShootController {

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ShootRepository shootRepository;

    @PostMapping("/{boardId}")
    public ResponseEntity<?> shoot(@PathVariable Long boardId, @RequestBody ShootRequest shootRequest, Authentication authentication) {

        String email = authentication.getName();
        Account account = accountService.findByEmail(email);
        if (account == null) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        Board board = boardRepository.findById(boardId).orElse(null);
        if (board == null) {
            return new ResponseEntity<>("Board not found", HttpStatus.NOT_FOUND);
        }
        Match match = board.getPlayerMatch().getMatch();
        if (match == null) {
            return new ResponseEntity<>("Board does not belong to any match", HttpStatus.NOT_FOUND);
        }
        PlayerMatch player1 = match.getPlayerMatches().stream()
                .filter(pm -> pm.getAccount().getId() == account.getId())
                .findFirst()
                .orElse(null);
        if (player1 == null) {
            return new ResponseEntity<>("User is not part of this match", HttpStatus.FORBIDDEN);
        }
        if (player1.getType() == PlayerStatus.READY){
            if (board.getPlayerMatch().getAccount().getId() == account.getId()) {
                return new ResponseEntity<>("Cannot shoot your own board", HttpStatus.BAD_REQUEST);
            }

            if (shootRequest.cordX() < 0 || shootRequest.cordX() >= 10 ||
                    shootRequest.cordX() < 0 || shootRequest.cordY() >= 10) {
                return new ResponseEntity<>("Invalid coordinates", HttpStatus.FORBIDDEN);
            }
            Coordinate shootCoordinate = new Coordinate(shootRequest.cordX(), shootRequest.cordY());
            System.out.println(shootCoordinate);
            List<Coordinate> usedCoordinates = board.getShips().stream()
                    .flatMap(ship -> ship.getCoordinates().stream())
                    .toList();


            if (existsCoordinate(shootCoordinate, usedCoordinates)) {


                Shoot shoot = new Shoot(shootCoordinate);
                board.addShoot(shoot);
                boardRepository.save(board);
                Coordinate hitCoordinate = usedCoordinates.stream().filter(c -> c.getX() == shootCoordinate.getX() && c.getY() == shootCoordinate.getY()).findFirst().orElse(null);
                hitCoordinate.setHit(true);
                Ship hitShip = hitCoordinate.getShip();
                hitShip.setStatus(ShipStatus.HIT);
                shoot.setResult(ShootResult.HIT);
                player1.setTurn(false);
                shootRepository.save(shoot);
                if (isShipSunk(hitShip)) {
                    hitShip.setStatus(ShipStatus.SUNK);
                    shipRepository.save(hitShip);

                    return new ResponseEntity<>("Ship sunk!", HttpStatus.OK);
                }
                return new ResponseEntity<>("Hit!", HttpStatus.OK);
            } else {
                Shoot shoot = new Shoot(shootCoordinate);
                board.addShoot(shoot);
                boardRepository.save(board);
                shoot.setResult(ShootResult.MISS);
                shootRepository.save(shoot);
                player1.setTurn(false);
                return new ResponseEntity<>("Miss!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User is not ready", HttpStatus.FORBIDDEN);
    }

    private boolean existsCoordinate (Coordinate coordinate, List<Coordinate> coordinates) {
        for (Coordinate c : coordinates) {
            if (c.getX() == coordinate.getX() && c.getY() == coordinate.getY()) {
                return true;
            }
        }
        return false;
    }

    private boolean isShipSunk(Ship ship) {
        for (Coordinate coordinate : ship.getCoordinates()) {
            if (!coordinate.isHit()) {
                return false;
            }
        }
        return true;
    }
 }




