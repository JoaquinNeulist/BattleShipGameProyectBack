package com.Battleship.Game.controllers;

import com.Battleship.Game.dtos.ShootRequest;
import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.BoardRepository;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.repositories.ShootRepository;
import com.Battleship.Game.services.AccountService;
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
        PlayerMatch player = match.getPlayerMatches().stream()
                .filter(pm -> pm.getAccount().getId() == account.getId())
                .findFirst()
                .orElse(null);
        if (player == null) {
            return new ResponseEntity<>("User is not part of this match", HttpStatus.FORBIDDEN);
        }
        if (player.getType() == PlayerStatus.READY){
            if (board.getPlayerMatch().getAccount().getId() == account.getId()) {
                return new ResponseEntity<>("Cannot shoot your own board", HttpStatus.BAD_REQUEST);
            }

            if (shootRequest.cordX() < 0 || shootRequest.cordX() >= 10 ||
                    shootRequest.cordX() < 0 || shootRequest.cordY() >= 10) {
                return new ResponseEntity<>("Invalid coordinates", HttpStatus.FORBIDDEN);
            }
            Coordinate shootCoordinate = new Coordinate(shootRequest.cordX(), shootRequest.cordY());
            Optional<Ship> optionalShip = board.getShips().stream()
                .filter(ship -> shipContainsCoordinate(ship, shootCoordinate))
                .findFirst();

            if (optionalShip.isPresent()) {
                Ship ship = optionalShip.get();

                ship.setStatus(ShipStatus.HIT);
                shipRepository.save(ship);

                if (isShipSunk(ship, board.getShoots())) {
                    ship.setStatus(ShipStatus.SUNK);
                    shipRepository.save(ship);
                }
                Shoot shoot = new Shoot(shootCoordinate);
                board.addShoot(shoot);
                boardRepository.save(board);
                shoot.setResult(ShootResult.HIT);
                shootRepository.save(shoot);
                return new ResponseEntity<>("Hit!", HttpStatus.OK);
            } else {
                Shoot shoot = new Shoot(shootCoordinate);
                board.addShoot(shoot);
                boardRepository.save(board);
                shoot.setResult(ShootResult.MISS);
                shootRepository.save(shoot);
                return new ResponseEntity<>("Miss!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User is not ready", HttpStatus.FORBIDDEN);
    }

    private boolean shipContainsCoordinate(Ship ship, Coordinate coordinate) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            List<String> shipCoordinates = objectMapper.readValue(ship.getCoordinates(), new TypeReference<List<String>>() {});
            for (String shipCoordinate : shipCoordinates){
                if (shipCoordinate.equals(coordinate.toString())){
                    return true;
                }
            }
            return false;
        }catch (IOException e){
            throw new RuntimeException("Error parsing coordinates", e);
        }
    }

    private boolean isShipSunk(Ship ship, List<Shoot> shoots) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            List<String> shipCoordinates = objectMapper.readValue(ship.getCoordinates(), new TypeReference<List<String>>() {});
            for (String shipCoordinate : shipCoordinates){
                boolean coordinateHit = false;
                for (Shoot shoot : shoots){
                    if (shoot.getCoordinates().contains(shipCoordinate)&&shoot.getResult() == ShootResult.HIT){
                        coordinateHit = true;
                        break;
                    }
                }
                if (!coordinateHit){
                    return false;
                }
            }
            return true;
        }catch (IOException e){
            throw new RuntimeException("Error parsing coordinates", e);
        }
    }

}


