package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.Board;
import com.Battleship.Game.models.Match;
import com.Battleship.Game.models.MatchState;
import com.Battleship.Game.models.PlayerMatch;
import com.Battleship.Game.repositories.MatchRepository;
import com.Battleship.Game.services.BoardService;
import com.Battleship.Game.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Match startNewMatch() {
        Match match = new Match();
        match.setState(MatchState.STARTED);
        match.setStartTime(LocalDateTime.now());
        return matchRepository.save(match);
    }

    @Override
    public Match endMatch(Match match) {
        return null;
    }

    @Override
    public Match getMatchById(Long id) {
        return null;
    }
}
