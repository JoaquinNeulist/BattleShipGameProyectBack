//package com.Battleship.Game;
//import com.Battleship.Game.models.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PlayerMatchTest {
//
//    private PlayerMatch playerMatch;
//    private Account account;
//    private Match match;
//
//    @BeforeEach
//    public void setUp() {
//        // Configurar el estado inicial para cada prueba
//        playerMatch = new PlayerMatch(PlayerStatus.WAITING_FOR_OPPONENT);
//        account = new Account(); // Ajusta según tus necesidades
//        match = new Match();     // Ajusta según tus necesidades
//    }
//
//    @Test
//    public void testPlayerMatchInitialization() {
//        assertNotNull(playerMatch);
//        assertEquals(PlayerStatus.WAITING_FOR_OPPONENT, playerMatch.getType());
//        assertFalse(playerMatch.isTurn());
//        assertNull(playerMatch.getAccount());
//        assertNull(playerMatch.getMatch());
//        assertTrue(playerMatch.getBoard().isEmpty());
//    }
//
//    @Test
//    public void testSettersAndGetters() {
//        playerMatch.setAccount(account);
//        playerMatch.setMatch(match);
//        playerMatch.setTurn(true);
//
//        assertEquals(account, playerMatch.getAccount());
//        assertEquals(match, playerMatch.getMatch());
//        assertTrue(playerMatch.isTurn());
//    }
//
//    @Test
//    public void testAddBoard() {
//        Board board = new Board(); // Ajusta según tus necesidades
//        playerMatch.addBoard(board);
//
//        assertEquals(1, playerMatch.getBoard().size());
//        assertTrue(playerMatch.getBoard().contains(board));
//        assertEquals(playerMatch, board.getPlayerMatch());
//    }
//
//    @Test
//    public void testGetBoard() {
//        Board board = new Board();
//        playerMatch.getBoard().add(board);
//        assertEquals(board, playerMatch.getBoard().get(0));
//    }
//}