package com.Battleship.Game.services;

import com.Battleship.Game.models.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingService {

    public void updateRankings();

    public Ranking findByUserEmail(String userEmail);

    List<Ranking> getAllRankings();
}
