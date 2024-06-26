package com.Battleship.Game.models;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Enumerated(EnumType.STRING)
    private ShipStatus status;

    private int size;

    @ManyToOne
    private Board board;

    @Column(columnDefinition = "json")
    private String coordinates;

//    private List<Coordinate> shipCoordinates;

    public Ship(){}

    public Ship(ShipType shipType, int size, ShipStatus status) {
        this.shipType = shipType;
        this.size = size;
        this.status = status;
    }

    public Ship(ShipType shipType, int size, ShipStatus status, String coordinates) {
        this.shipType = shipType;
        this.size = size;
        this.status = status;
        this.coordinates = coordinates;

    }

    public long getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

//    public List<Coordinate> getShipCoordinates(){
//        if (shipCoordinates == null && coordinates != null){
//            ObjectMapper objectMapper = new ObjectMapper();
//            try{
//                shipCoordinates = objectMapper.readValue(coordinates,
//                        objectMapper.getTypeFactory().constructCollectionType(List.class, Coordinate.class));
//            } catch (JsonProcessingException e){
//                e.printStackTrace();
//            }
//        }
//        return shipCoordinates;
//    }
//
//    public void setShipCoordinates(List<Coordinate> shipCoordinates) {
//        this.shipCoordinates = shipCoordinates;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            this.coordinates = objectMapper.writeValueAsString(shipCoordinates);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
}
