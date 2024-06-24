package com.Battleship.Game.services.implement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.Battleship.Game.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Battleship.Game.models.Ranking;
import com.Battleship.Game.repositories.RankingRepository;
import com.Battleship.Game.services.AccountService;

@Service
@Transactional
public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private AccountService accountService;

    public void updateRankings() {
        List<Ranking> rankings = rankingRepository.findAll();
        Collections.sort(rankings); // Ordenar por puntaje descendente
        for (int i = 0; i < rankings.size(); i++) {
            Ranking ranking = rankings.get(i);
            ranking.setPosition(i + 1); // Establecer nueva posición
            rankingRepository.save(ranking); // Guardar el ranking actualizado
        }
    }

    public Ranking findByUserEmail(String userEmail) {
        return accountService.findByEmail(userEmail).getRanking();
    }

    public List<Ranking> getAllRankings() {
        List<Ranking> rankings = rankingRepository.findAll();

        // Ordenar por score en orden descendente
        Collections.sort(rankings, new Comparator<Ranking>() {
            @Override
            public int compare(Ranking r1, Ranking r2) {
                // Orden descendente (r2 - r1)
                return r2.getScore() - r1.getScore();
            }
        });
        return rankings;
    }
}
