
//package com.Battleship.Game.services.implement;
//
//import com.Battleship.Game.services.GameService;
//import com.Battleship.Game.services.PlayerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class GameServiceImpl implements GameService {
//
//    @Autowired
//    private PlayerService playerService;
//
//    @Override
//    public void handleGameEnd(Long winnerId, Long loserId){
//        playerService.updatePlayerScore(winnerId, 200);
//        playerService.updatePlayerScore(loserId, -100);
//
//    }
//}
//
