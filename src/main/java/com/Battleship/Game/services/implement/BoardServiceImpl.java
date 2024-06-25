package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.ShipRepository;
import com.Battleship.Game.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public Board createBoard(String configuration) {
        Board board = new Board();
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                board.addCoordinate(new Coordinate(row, column));
            }
        }
        board.setConfiguration(configuration);
        return board;
    }

    @Override
    public Board placeShip(Board board, Ship ship) {
        if (canPlaceShip(board, ship, ship.getStartingX(), ship.getStartingY(), ship.isSideways())) {
            board.addShip(ship);
            ship.setBoard(board);
        }
        return board;
    }

    @Override
    public Shoot registerShoot(Board board, int x, int y) {
        ShootResult result = calculateShootResult(board, x, y);
        Shoot shoot = new Shoot(x, y, result);
        board.addShoot(shoot);
        return shoot;
    }

    @Override
    public ShootResult calculateShootResult(Board board, int x, int y) {
        for (Ship ship : board.getShips()) {
            if (isHit(ship, x, y)) {
                ship.setStatus(ShipStatus.HIT);
                if (isShipSunk(ship)) {
                    ship.setStatus(ShipStatus.SUNK);
                    if (allShipsSunk(board)) {
                        return ShootResult.WIN;
                    } else {
                        return ShootResult.SUNK;
                    }
                } else {
                    return ShootResult.HIT;
                }
            }
        }
        return ShootResult.MISS;
    }

    @Override
    public boolean canPlaceShip(Board board, Ship ship, int startX, int startY, boolean isHorizontal) {
        int shipSize = ship.getSize();
        if (isHorizontal) {
            if (startX + shipSize > 10) return false;
            for (int i = 0; i < shipSize; i++) {
                if (isOccupied(board, startX + i, startY)) return false;
            }
        } else {
            if (startY + shipSize > 10) return false;
            for (int i = 0; i < shipSize; i++) {
                if (isOccupied(board, startX, startY + i)) return false;
            }
        }
        return true;
    }

    @Override
    public boolean allShipsSunk(Board board) {
        for (Ship ship : board.getShips()) {
            if (ship.getStatus() != ShipStatus.SUNK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public BoardStatus getBoardStatus(Board board) {
        BoardStatus boardStatus = new BoardStatus();
        boardStatus.setShips(board.getShips());
        boardStatus.setShoots(board.getShoots());
        return boardStatus;
    }

    @Override
    public Board initializeBoard(Board board, List<Ship> ships) {
        for (Ship ship : ships) {
            placeShip(board, ship);
        }
        return board;
    }

    private boolean isOccupied(Board board, int x, int y) {
        for (Ship ship : board.getShips()) {
            int startX = ship.getStartingX();
            int startY = ship.getStartingY();
            int shipSize = ship.getSize();
            if (ship.isSideways()) {
                if (y == startY && x >= startX && x < startX + shipSize) {
                    return true;
                }
            } else {
                if (x == startX && y >= startY && y < startY + shipSize) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isShipSunk(Ship ship) {
        List<Coordinate> boardCoordinates = ship.getBoard().getCoordinates();
        for (Coordinate coordinate : boardCoordinates) {
            if (coordinate.getRow() == ship.getStartingX() && coordinate.getColumn() == ship.getStartingY()) {
                if (coordinate.getStatus() != CoordinateStatus.HIT) {
                    return false;
                }
                if (ship.isSideways()) {
                    for (int i = 1; i < ship.getSize(); i++) {
                        Coordinate nextCoordinate = new Coordinate(ship.getStartingX(), ship.getStartingY() + i);
                        if (!boardCoordinates.contains(nextCoordinate) || boardCoordinates.get(boardCoordinates.indexOf(nextCoordinate)).getStatus() != CoordinateStatus.HIT) {
                            return false;
                        }
                    }
                } else {
                    for (int i = 1; i < ship.getSize(); i++) {
                        Coordinate nextCoordinate = new Coordinate(ship.getStartingX() + i, ship.getStartingY());
                        if (!boardCoordinates.contains(nextCoordinate) || boardCoordinates.get(boardCoordinates.indexOf(nextCoordinate)).getStatus() != CoordinateStatus.HIT) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean isHit(Ship ship, int x, int y) {
        int startX = ship.getStartingX();
        int startY = ship.getStartingY();
        int shipSize = ship.getSize();
        if (ship.isSideways()) {
            return (y == startY) && (x >= startX) && (x < (startX + shipSize));
        } else {
            return (x == startX) && (y >= startY) && (y < (startY + shipSize));
        }
    }
}
