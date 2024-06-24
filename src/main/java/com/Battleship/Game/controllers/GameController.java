package com.Battleship.Game.controllers;

import com.Battleship.Game.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/end")
    public void endGame(@RequestBody GameEndRequest request){
        gameService.handleGameEnd(request.getWinnerId(), request.getLoserId());
    }

    public static class GameEndRequest{
        private long winnerId;
        private long loserId;

        public long getLoserId() {
            return loserId;
        }

        public long getWinnerId() {
            return winnerId;
        }

        public void setLoserId(long loserId) {
            this.loserId = loserId;
        }

        public void setWinnerId(long winnerId) {
            this.winnerId = winnerId;
        }
    }
}
