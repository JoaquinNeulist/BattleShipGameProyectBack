package com.Battleship.Game.services;

import com.Battleship.Game.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {

    Board createBoard(String configuration);

    Board placeShip(Board board, Ship ship);

    Shoot registerShoot(Board board, int x, int y);

    ShootResult calculateShootResult(Board board, int x, int y);

    boolean canPlaceShip(Board board, Ship ship, int startX, int startY, boolean isHorizontal);

    boolean allShipsSunk(Board board);

    BoardStatus getBoardStatus(Board board);

    Board initializeBoard(Board board, List<Ship> ships);
}
