package com.Battleship.Game.services.implement;

import com.Battleship.Game.dtos.ShootRequest;
import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.ShootRepository;
import com.Battleship.Game.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShootServiceImpl implements ShootService {
    @Autowired
    private ShootRepository shootRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ShipService shipService;

    @Override
    public void saveShoot(Shoot shoot) {
         shootRepository.save(shoot);
    }

    @Override
    public ResponseEntity<?> generateShoot(Long boardId, ShootRequest shootRequest, Authentication authentication) {
        String email = authentication.getName();
        Account account = accountService.findByEmail(email);
        if (account == null) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }
        Board board = boardService.findById(boardId).orElse(null);
        if (board == null) {
            return new ResponseEntity<>("Board not found", HttpStatus.NOT_FOUND);
        }
        List<Shoot> shoots = board.getShoots();
        Match match = board.getPlayerMatch().getMatch();
        if (match == null) {
            return new ResponseEntity<>("Board does not belong to any match", HttpStatus.NOT_FOUND);
        }
        PlayerMatch player1 = match.getPlayerMatches().stream()
                .filter(pm -> pm.getAccount().getId() == account.getId())
                .findFirst()
                .orElse(null);
        PlayerMatch player2 = match.getPlayerMatches().stream()
                .filter(pm -> pm.getAccount().getId() != account.getId()).findFirst().orElse(null);
        if (player1 == null) {
            return new ResponseEntity<>("User is not part of this match", HttpStatus.FORBIDDEN);
        }
        if (player1.getType() == PlayerStatus.READY){
            if (!player1.isTurn() && player2.isTurn()){
                return new ResponseEntity<>("It's not your turn", HttpStatus.FORBIDDEN);
            }
            if (board.getPlayerMatch().getAccount().getId() == account.getId()) {
                return new ResponseEntity<>("Cannot shoot your own board", HttpStatus.BAD_REQUEST);
            }


            if (shootRequest.cordX() < 0 || shootRequest.cordX() >= 10 ||
                    shootRequest.cordX() < 0 || shootRequest.cordY() >= 10) {
                return new ResponseEntity<>("Invalid coordinates", HttpStatus.FORBIDDEN);
            }
            Coordinate shootCoordinate = new Coordinate(shootRequest.cordX(), shootRequest.cordY());
            System.out.println(shootCoordinate);
            if (isCoordinateUsed(shootCoordinate, shoots)) {
                return new ResponseEntity<>("Coordinate already hit", HttpStatus.FORBIDDEN);
            }
            List<Coordinate> usedCoordinates = board.getShips().stream()
                    .flatMap(ship -> ship.getCoordinates().stream())
                    .toList();

            if (existsCoordinate(shootCoordinate, usedCoordinates)) {
                Shoot shoot = new Shoot(shootCoordinate);
                board.addShoot(shoot);
                boardService.saveBoard(board);
                Coordinate hitCoordinate = usedCoordinates.stream().filter(c -> c.getX() == shootCoordinate.getX() && c.getY() == shootCoordinate.getY()).findFirst().orElse(null);
                hitCoordinate.setHit(true);
                Ship hitShip = hitCoordinate.getShip();
                hitShip.setStatus(ShipStatus.HIT);
                shoot.setResult(ShootResult.HIT);
                player1.setTurn(false);
                player2.setTurn(true);
                playerService.savePlayerMatch(player1);
                playerService.savePlayerMatch(player2);
                shootRepository.save(shoot);
                shipService.saveShip(hitShip);
                if (isShipSunk(hitShip)) {
                    hitShip.setStatus(ShipStatus.SUNK);
                    shipService.saveShip(hitShip);
                    if (areAllShipsSunk(usedCoordinates)) {
                        match.setState(MatchState.FINISHED);
                        player1.setType(PlayerStatus.WIN);
                        player1.getAccount().setScore(player1.getAccount().getScore() + 200);
                        player2.setType(PlayerStatus.LOSE);
                        player2.getAccount().setScore(player2.getAccount().getScore() - 200);
                        if (player2.getAccount().getScore() < 0) {
                            player2.getAccount().setScore(0);
                        }
                        playerService.savePlayerMatch(player1);
                        playerService.savePlayerMatch(player2);
                        matchService.saveMatch(match);
                        return new ResponseEntity<>("You won!", HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Ship sunk!", HttpStatus.OK);
                }
                return new ResponseEntity<>("Hit!", HttpStatus.OK);
            } else {
                Shoot shoot = new Shoot(shootCoordinate);
                board.addShoot(shoot);
                boardService.saveBoard(board);
                shoot.setResult(ShootResult.MISS);
                shootRepository.save(shoot);
                player1.setTurn(false);
                player2.setTurn(true);
                playerService.savePlayerMatch(player1);
                playerService.savePlayerMatch(player2);
                return new ResponseEntity<>("Miss!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User is not ready", HttpStatus.FORBIDDEN);
    }

private boolean areAllShipsSunk(List<Coordinate> coordinates) {
    return coordinates.stream().allMatch(Coordinate::isHit);
}

private boolean isCoordinateUsed(Coordinate coordinate, List<Shoot> shoots) {
    for (Shoot s : shoots) {
        if (s.getCordX() == coordinate.getX() && s.getCordY() == coordinate.getY()) {
            return true;
        }
    }
    return false;
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
