package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.Board;
import com.Battleship.Game.models.Coordinate;
import com.Battleship.Game.services.BoardService;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    @Override
    public Board createBoard(String configuration) {
        Board board = new Board();
        // Crear coordenadas para cada casilla del juego
        for (char row = 'A'; row <= 'J'; row++) {
            for (int column = 1; column <= 10; column++) {
                board.addCoordinate(new Coordinate(row - 'A' + 1, column));
            }
        }
        board.setConfiguration(configuration);
        return board;
    }
}
