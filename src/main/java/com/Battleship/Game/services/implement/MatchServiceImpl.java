package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.Board;
import com.Battleship.Game.models.PlayerMatch;
import com.Battleship.Game.services.BoardService;
import com.Battleship.Game.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchServiceImpl implements MatchService {

    @Autowired
    private BoardService boardService;

    @Override
    public void startMatch(PlayerMatch player1, PlayerMatch player2) {
//        Board boardPlayer1 = boardService.createBoard();
//        Board boardPlayer2 = boardService.createBoard();

    }
}
