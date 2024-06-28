package com.Battleship.Game.services;

import com.Battleship.Game.dtos.MatchDTO;
import com.Battleship.Game.models.Match;
import com.Battleship.Game.models.PlayerMatch;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {

    void saveMatch(Match match);

    MatchDTO createMatch(Authentication authentication);

    Match joinMatch(Authentication authentication, String partyCode);

    MatchDTO getCurrentMatch(Authentication authentication);


}
