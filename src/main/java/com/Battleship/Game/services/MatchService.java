package com.Battleship.Game.services;

import com.Battleship.Game.models.Match;
import com.Battleship.Game.models.PlayerMatch;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {

    Match startNewMatch();

    Match endMatch(Match match);

    Match getMatchById(Long id);

    Match joinMatch(Long matchId, PlayerMatch player);

    Match leaveMatch(Long matchId, Long playerId);

    Match updateMatch(Long matchId, Match updatedMatch);

    void deleteMatch(Long matchId);

}
