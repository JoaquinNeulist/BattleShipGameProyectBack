package com.Battleship.Game.services;

import com.Battleship.Game.models.Match;
import com.Battleship.Game.models.PlayerMatch;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {

    Match startNewMatch();

    Match endMatch(Match match);

    Match getMatchById(Long id);

}
