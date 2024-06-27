package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.BoardRepository;
import com.Battleship.Game.repositories.CoordinateRepository;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.services.AccountService;
import com.Battleship.Game.services.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private CoordinateRepository coordinateRepository;

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
                if (!areCoordinatesValid(shipRequest.getCoordinates()) || tenToTenTable(shipRequest.getCoordinates())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid coordinates for ship: " + shipRequest.getType());
                }

                for (Coordinate coordinate : shipRequest.getCoordinates()) {
                    if (!usedCoordinates.add(coordinate)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coordinate already in use: " + coordinate.getX() + "," + coordinate.getY());
                    }
                    System.out.println(usedCoordinates);
                }

                Ship ship = new Ship();
                for (Coordinate coordinate : shipRequest.getCoordinates()) {
                    ship.addCoordinate(coordinate);
                }
                ship.setShipType(ShipType.valueOf(shipRequest.getType().toUpperCase()));
                ship.setSize(shipRequest.getCoordinates().size());
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
        coordinateRepository.saveAll(usedCoordinates);

        return ResponseEntity.ok("Ships added successfully");
    }

    @Override
    public Optional<Board> findById(Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Override
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    private boolean tenToTenTable(List<Coordinate> coordinates) {
        for (Coordinate coordinate : coordinates) {
            if (coordinate.getX() < 0 || coordinate.getX() > 10 || coordinate.getY() < 0 || coordinate.getY() > 10) {
                return true;
            }
        }
        return false;
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
