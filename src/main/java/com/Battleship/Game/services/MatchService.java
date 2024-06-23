package com.Battleship.Game.services;

import com.Battleship.Game.models.PlayerMatch;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {

    void startMatch(PlayerMatch player1, PlayerMatch player2);
}
