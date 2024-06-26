package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.BoardRepository;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.services.AccountService;
import com.Battleship.Game.services.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> createBoard(@PathVariable Long boardId, @RequestBody BoardRequest boardRequest, Authentication authentication) {
        String email = authentication.getName();


        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found");
        }

        Board board = optionalBoard.get();
        if (!board.getPlayerMatch().getMail().equals(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access to board");
        }
        PlayerMatch player = board.getPlayerMatch();
        List<Ship> ships = new ArrayList<>();
        Set<Coordinate> usedCoordinates = new HashSet<>();
        if (player.getType() == PlayerStatus.PLACING_SHIPS){
            for (ShipRequest shipRequest : boardRequest.getShips()) {
                if (!areCoordinatesValid(shipRequest.getCoordinates())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid coordinates for ship: " + shipRequest.getType());
                }

                for (Coordinate coordinate : shipRequest.getCoordinates()) {
                    if (!usedCoordinates.add(coordinate)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coordinate already in use: " + coordinate.getX() + "," + coordinate.getY());
                    }
                    System.out.println(usedCoordinates);
                }

                Ship ship = new Ship();
                ship.setShipType(ShipType.valueOf(shipRequest.getType().toUpperCase()));
                ship.setSize(shipRequest.getCoordinates().size());
                ship.setCoordinates(convertCoordinatesToJson(shipRequest.getCoordinates()));
                ship.setStatus(ShipStatus.INTACT);
                board.addShip(ship);
                ships.add(ship);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player is ready");
        }

        player.setType(PlayerStatus.READY);
        boardRepository.save(board);
        shipRepository.saveAll(ships);

        return ResponseEntity.ok("Ships added successfully");
    }

    private String convertCoordinatesToJson(List<Coordinate> coordinates) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(coordinates);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting coordinates to JSON", e);
        }
    }

    private boolean areCoordinatesValid(List<Coordinate> coordinates) {
        if (coordinates.isEmpty()) {
            return false;
        }
        boolean isHorizontal = coordinates.stream().allMatch(c -> c.getY() == coordinates.get(0).getY());
        boolean isVertical = coordinates.stream().allMatch(c -> c.getX() == coordinates.get(0).getX());
        if (!isHorizontal && !isVertical) {
            return false;
        }
        if (isHorizontal) {
            coordinates.sort(Comparator.comparingInt(Coordinate::getX));
            for (int i = 1; i < coordinates.size(); i++) {
                if (coordinates.get(i).getX() != coordinates.get(i - 1).getX() + 1) {
                    return false;
                }
            }
        } else {
            coordinates.sort(Comparator.comparingInt(Coordinate::getY));
            for (int i = 1; i < coordinates.size(); i++) {
                if (coordinates.get(i).getY() != coordinates.get(i - 1).getY() + 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
