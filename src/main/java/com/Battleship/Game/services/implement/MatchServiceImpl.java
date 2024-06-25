package com.Battleship.Game.services.implement;

import com.Battleship.Game.dtos.MatchDTO;
import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.AccountRepository;
import com.Battleship.Game.repositories.MatchRepository;
import com.Battleship.Game.services.MatchService;
import com.Battleship.Game.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PlayerService playerService;

    @Override
    public MatchDTO createMatch(Authentication authentication) {
        String username = authentication.getName();
        Account account = accountRepository.findByEmail(username);
        if (account == null){
            throw new IllegalStateException("Account not found");
        }
        Match match = new Match(MatchState.WAITING);
        String partyCode = match.getPartyCode();
        PlayerMatch player1 = new PlayerMatch();

        player1.setAccount(account);
        player1.setType(PlayerStatus.WAITING_FOR_OPPONENT);
        playerService.savePlayerMatch(player1);
        match.addPlayersMatch(player1);
        matchRepository.save(match);
        return new MatchDTO(match);
    }

    @Override
    public Match joinMatch(Authentication authentication, String partyCode){
        String username = authentication.getName();
        Account account = accountRepository.findByEmail(username);
        if (account == null){
            throw new IllegalStateException("Account not found");
        }
        PlayerMatch player2 = new PlayerMatch();
        player2.setAccount(account);
        Match match = matchRepository.findByPartyCode(partyCode);
        if (match == null){
            throw new IllegalStateException("Match not found");
        }
        if (match.getState() != MatchState.WAITING){
            throw new IllegalStateException("Match already started");
        }
        match.setState(MatchState.STARTED);
        player2.setType(PlayerStatus.PLACING_SHIPS);
        match.addPlayersMatch(player2);
        match.setStartTime(LocalDateTime.now());
        match.setFinalTime(LocalDateTime.now().plusMinutes(30));
        player2.setType(PlayerStatus.WAITING);
        playerService.savePlayerMatch(player2);
        matchRepository.save(match);
        return match;
    }
}
