//package com.Battleship.Game;
//import com.Battleship.Game.models.Match;
//import com.Battleship.Game.models.MatchState;
//import com.Battleship.Game.models.PlayerMatch;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class MatchTest {
//
//    @Test
//    public void testMatchCreationWithState() {
//        Match match = new Match(MatchState.WAITING);
//        assertEquals(MatchState.WAITING, match.getState());
//        assertNotNull(match.getPartyCode());
//    }
//
//    @Test
//    public void testMatchCreationWithStateAndTimes() {
//        LocalDateTime startTime = LocalDateTime.now();
//        Match match = new Match(MatchState.STARTED, startTime, startTime.plusMinutes(30));
//        assertEquals(MatchState.STARTED, match.getState());
//        assertNotNull(match.getPartyCode());
//        assertEquals(startTime, match.getStartTime());
//        assertEquals(startTime.plusMinutes(30), match.getFinalTime());
//    }
//
//    @Test
//    public void testAddPlayerMatch() {
//        Match match = new Match();
//        PlayerMatch playerMatch = new PlayerMatch();
//        match.addPlayersMatch(playerMatch);
//        assertTrue(match.getPlayerMatches().contains(playerMatch));
//        assertEquals(match, playerMatch.getMatch());
//    }
//
//    @Test
//    public void testGetPlayerMatches() {
//        Match match = new Match();
//        PlayerMatch playerMatch1 = new PlayerMatch();
//        PlayerMatch playerMatch2 = new PlayerMatch();
//        match.addPlayersMatch(playerMatch1);
//        match.addPlayersMatch(playerMatch2);
//        List<PlayerMatch> playerMatches = match.getPlayerMatches();
//        assertEquals(2, playerMatches.size());
//        assertTrue(playerMatches.contains(playerMatch1));
//        assertTrue(playerMatches.contains(playerMatch2));
//    }
//}