package com.Battleship.Game.services.implement;

import com.Battleship.Game.dtos.MatchDTO;
import com.Battleship.Game.models.*;
import com.Battleship.Game.repositories.AccountRepository;
import com.Battleship.Game.repositories.BoardRepository;
import com.Battleship.Game.repositories.MatchRepository;
import com.Battleship.Game.services.AccountService;
import com.Battleship.Game.services.MatchService;
import com.Battleship.Game.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BoardRepository boardRepository;

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
        PlayerMatch player1 = new PlayerMatch();
        Board board2 = new Board();
        player1.setAccount(account);
        player1.setType(PlayerStatus.WAITING_FOR_OPPONENT);
        player1.addBoard(board2);
        playerService.savePlayerMatch(player1);
        boardRepository.save(board2);
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
        Board board1 = new Board();
        match.addPlayersMatch(player2);
        player2.addBoard(board1);
        match.setStartTime(LocalDateTime.now());
        match.setFinalTime(LocalDateTime.now().plusMinutes(30));
        match.getPlayerMatches().get(0).setType(PlayerStatus.PLACING_SHIPS);
        PlayerMatch player1 = match.getPlayerMatches().stream().filter(pm -> pm.getAccount().getId() != account.getId()).findFirst().orElse(null);
        setRandomTurn(player1, player2);
        playerService.savePlayerMatch(player1);
        playerService.savePlayerMatch(player2);
        boardRepository.save(board1);
        matchRepository.save(match);
        return match;
    }

    @Override
    public void saveMatch(Match match) {
        matchRepository.save(match);
    }

    private final Random random = new Random();

    public void setRandomTurn(PlayerMatch player1, PlayerMatch player2) {
        if (random.nextBoolean()) {
            player1.setTurn(true);
            player2.setTurn(false);
        } else {
            player1.setTurn(false);
            player2.setTurn(true);
        }
    }

    @Override
    public MatchDTO getCurrentMatch(Authentication authentication) {
        Account account = accountService.findByEmail(authentication.getName());
        if (account == null){
            throw new IllegalStateException("Account not found");
        }

        List<PlayerMatch> playerMatches = account.getPlayersInMatch();
        List<Match> matches = playerMatches.stream().map(pm -> pm.getMatch()).collect(Collectors.toList());

        for (PlayerMatch playerMatch : playerMatches) {
            if (playerMatch.getType() == PlayerStatus.WAITING_FOR_OPPONENT || playerMatch.getType() == PlayerStatus.READY || playerMatch.getType() == PlayerStatus.PLACING_SHIPS) {
                return new MatchDTO(playerMatch.getMatch());
            }
        }
        return null;
    }
}
