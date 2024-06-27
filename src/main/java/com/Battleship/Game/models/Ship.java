package com.Battleship.Game.models;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    // Método para deserializar el JSON de coordinates a una lista de objetos Coordinate
    public List<Coordinate> getShipCoordinates() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(coordinates,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Coordinate.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para serializar una lista de objetos Coordinate a JSON y almacenarla en coordinates
    public void setShipCoordinates(List<Coordinate> shipCoordinates) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.coordinates = objectMapper.writeValueAsString(shipCoordinates);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
