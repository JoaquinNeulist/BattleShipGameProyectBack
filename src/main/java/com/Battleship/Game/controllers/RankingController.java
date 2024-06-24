package com.Battleship.Game.controllers;

import com.Battleship.Game.models.Ranking;
import com.Battleship.Game.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/general")
    public List<Ranking> getGeneralRankings() {
        return rankingService.getAllRankings();
    }
}