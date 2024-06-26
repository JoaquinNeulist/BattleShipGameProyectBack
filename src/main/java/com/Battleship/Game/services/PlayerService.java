package com.Battleship.Game.services;

import com.Battleship.Game.models.Board;
import com.Battleship.Game.models.MatchState;
import com.Battleship.Game.models.PlayerMatch;
import com.Battleship.Game.models.PlayerStatus;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {

    void updatePlayerStatus(Long playerId, PlayerStatus newStatus);

    void updatePlayerScore(Long playerId, int score);

    void addBoardToPlayerMatch(Long playerMatchId, Board board);

    void savePlayerMatch(PlayerMatch playerMatch);

}
