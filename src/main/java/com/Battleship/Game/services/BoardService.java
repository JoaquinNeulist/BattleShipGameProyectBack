package com.Battleship.Game.services;

import com.Battleship.Game.models.Board;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {

    Board createBoard(String configuration);
}
