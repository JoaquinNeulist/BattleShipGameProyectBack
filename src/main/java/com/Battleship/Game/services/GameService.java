package com.Battleship.Game.services;

import org.springframework.stereotype.Service;

@Service
public interface GameService {
    void handleGameEnd(Long winnerId, Long loserId);
}
