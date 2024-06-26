package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.PlayerMatchRepository;
import com.Battleship.Game.services.BoardService;
import com.Battleship.Game.services.PlayerService;
import com.Battleship.Game.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMatchRepository playerMatchRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BoardService boardService;

    @Override
    public void updatePlayerStatus(Long playerId, PlayerStatus newStatus) {
        PlayerMatch playerMatch = playerMatchRepository.findById(playerId).orElse(null);
        if (playerMatch != null) {
            playerMatch.setType(newStatus);
        }
    }

    @Override
    public void addBoardToPlayerMatch(Long playerMatchId, Board board) {
        PlayerMatch playerMatch = playerMatchRepository.findById(playerMatchId).orElse(null);
        if (playerMatch != null) {
            playerMatch.addBoard(board);
        }
    }

    @Override
    public void updatePlayerScore(Long playerId, int score) {
        PlayerMatch playerMatch = playerMatchRepository.findById(playerId).orElse(null);
    }

    @Override
    public void savePlayerMatch(PlayerMatch playerMatch) {
        playerMatchRepository.save(playerMatch);
    }
}

