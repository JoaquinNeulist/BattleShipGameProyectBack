package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.MatchRepository;
import com.Battleship.Game.services.BoardService;
import com.Battleship.Game.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
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
        match.setState(MatchState.FINISHED);
        match.setFinalTime(LocalDateTime.now());
        return matchRepository.save(match);
    }

    @Override
    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    @Override
    public Match joinMatch (Long matchId, PlayerMatch player){
        Match match = matchRepository.findById(matchId).orElse(null);
        if (match != null){
            match.addPlayersMatch(player);
            return matchRepository.save(match);
        }
        return null;
    }

    @Override
    public void deleteMatch(Long matchId) {
        matchRepository.deleteById(matchId);
    }

    @Override
    public Match leaveMatch(Long matchId, Long playerId) {
        Match match = matchRepository.findById(matchId).orElse(null);
        if (match != null){
            List<PlayerMatch> players = match.getPlayerMatches();
            for (PlayerMatch player : players){
                if (player.getId() == playerId){
                    players.remove(player);
                    match.setPlayerMatches(players);
                    return matchRepository.save(match);
                }
            }
        }
        return null;
    }

    @Override
    public Match updateMatch(Long matchId, Match updatedMatch) {
        Match match = matchRepository.findById(matchId).orElse(null);
        if (match != null){
            match.setStartTime(updatedMatch.getStartTime());
            match.setFinalTime(updatedMatch.getFinalTime());
            match.setState(updatedMatch.getState());
            return matchRepository.save(match);
        }
        return null;
    }
}
