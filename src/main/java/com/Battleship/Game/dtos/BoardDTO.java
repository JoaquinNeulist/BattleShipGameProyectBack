package com.Battleship.Game.dtos;
import com.Battleship.Game.dtos.ShootDTO;
import com.Battleship.Game.models.Board;

import java.util.List;
import java.util.stream.Collectors;

public class BoardDTO {
    private long id;
    private String configuration;
    private List<ShipDTO> ships;
    private List<ShootDTO> shoots;

    // Constructor con argumentos
    public BoardDTO(Board board) {
        this.id = board.getId();
        this.configuration = board.getConfiguration();
        this.ships = board.getShips().stream().map(ship -> new ShipDTO(ship)).collect(Collectors.toList());
        this.shoots = board.getShoots().stream().map(shoot -> new ShootDTO(shoot)).collect(Collectors.toList());
    }

    // Getters

    public long getId() {
        return id;
    }

    public String getConfiguration() {
        return configuration;
    }

    public List<ShipDTO> getShips() {
        return ships;
    }

    public List<ShootDTO> getShoots() {
        return shoots;
    }
}
