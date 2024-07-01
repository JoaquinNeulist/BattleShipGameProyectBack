//package com.Battleship.Game;
//import com.Battleship.Game.models.Board;
//import com.Battleship.Game.models.Coordinate;
//import com.Battleship.Game.models.Shoot;
//import com.Battleship.Game.models.ShootResult;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ShootTest {
//
//    @Test
//    public void testShootCreationWithCoordinates() {
//        Coordinate coordinate = new Coordinate(5, 6);
//        Shoot shoot = new Shoot(coordinate);
//
//        assertEquals(5, shoot.getCordX());
//        assertEquals(6, shoot.getCordY());
//        assertNull(shoot.getResult());
//    }
//
//    @Test
//    public void testShootCreationWithResult() {
//        Shoot shoot = new Shoot(3, 4, ShootResult.HIT);
//
//        assertEquals(3, shoot.getCordX());
//        assertEquals(4, shoot.getCordY());
//        assertEquals(ShootResult.HIT, shoot.getResult());
//    }
//
//    @Test
//    public void testShootAttributes() {
//        Shoot shoot = new Shoot(2, 3, ShootResult.MISS);
//
//        assertEquals(2, shoot.getCordX());
//        assertEquals(3, shoot.getCordY());
//        assertEquals(ShootResult.MISS, shoot.getResult());
//    }
//
//    @Test
//    public void testModifyAttributes() {
//        Shoot shoot = new Shoot(1, 2, ShootResult.HIT);
//        shoot.setCordX(7);
//        shoot.setCordY(8);
//        shoot.setResult(ShootResult.MISS);
//
//        assertEquals(7, shoot.getCordX());
//        assertEquals(8, shoot.getCordY());
//        assertEquals(ShootResult.MISS, shoot.getResult());
//    }
//
//    @Test
//    public void testSetBoard() {
//        Shoot shoot = new Shoot(3, 4, ShootResult.HIT);
//        Board board = new Board();
//        shoot.setBoard(board);
//
//        assertEquals(board, shoot.getBoard());
//    }
//}
