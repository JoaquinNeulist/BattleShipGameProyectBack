//package com.Battleship.Game;
//import com.Battleship.Game.models.Coordinate;
//import com.Battleship.Game.models.Ship;
//import com.Battleship.Game.models.ShipStatus;
//import com.Battleship.Game.models.ShipType;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ShipTest {
//
//    @Test
//    public void testShipCreation() {
//        Ship ship = new Ship(ShipType.BATTLESHIP, 4, ShipStatus.INTACT);
//        assertEquals(ShipType.BATTLESHIP, ship.getShipType());
//        assertEquals(4, ship.getSize());
//        assertEquals(ShipStatus.INTACT, ship.getStatus());
//    }
//
//    @Test
//    public void testShipAttributes() {
//        Ship ship = new Ship(ShipType.CRUISER, 3, ShipStatus.HIT);
//        assertEquals(ShipType.CRUISER, ship.getShipType());
//        assertEquals(3, ship.getSize());
//        assertEquals(ShipStatus.HIT, ship.getStatus());
//    }
//
//    @Test
//    public void testAddCoordinates() {
//        Ship ship = new Ship(ShipType.DESTROYER, 2, ShipStatus.INTACT);
//        Coordinate coord1 = new Coordinate(1, 1);
//        Coordinate coord2 = new Coordinate(1, 2);
//
//        ship.addCoordinate(coord1);
//        ship.addCoordinate(coord2);
//
//        List<Coordinate> coordinates = ship.getCoordinates();
//        assertEquals(2, coordinates.size());
//        assertTrue(coordinates.contains(coord1));
//        assertTrue(coordinates.contains(coord2));
//        assertEquals(ship, coord1.getShip());
//        assertEquals(ship, coord2.getShip());
//    }
//
//    @Test
//    public void testModifyAttributes() {
//        Ship ship = new Ship(ShipType.SUBMARINE, 3, ShipStatus.INTACT);
//        ship.setSize(4);
//        ship.setShipType(ShipType.BATTLESHIP);
//        ship.setStatus(ShipStatus.HIT);
//
//        assertEquals(4, ship.getSize());
//        assertEquals(ShipType.BATTLESHIP, ship.getShipType());
//        assertEquals(ShipStatus.HIT, ship.getStatus());
//    }
//
//    @Test
//    public void testSetCoordinates() {
//        Ship ship = new Ship(ShipType.SUBMARINE, 3, ShipStatus.INTACT);
//        List<Coordinate> coordinates = new ArrayList<>();
//        coordinates.add(new Coordinate(2, 3));
//        coordinates.add(new Coordinate(2, 4));
//
//        ship.setCoordinates(coordinates);
//        assertEquals(2, ship.getCoordinates().size());
//        assertEquals(coordinates, ship.getCoordinates());
//    }
//}