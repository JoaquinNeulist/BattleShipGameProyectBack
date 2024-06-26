package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Shoot;
import com.Battleship.Game.models.ShootResult;
import jakarta.persistence.Column;

public class ShootDTO {
    private long id;

    private ShootResult result;

    public int cordX;
    public int cordY;

    private String coordinates;
    // Constructor con argumentos
    public ShootDTO(Shoot shoot) {
        this.id = shoot.getId();
        this.cordX = shoot.getCordX();
        this.cordY = shoot.getCordY();
        this.result = shoot.getResult();
    }

    // Getters

    public long getId() {
        return id;
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public ShootResult getResult() {
        return result;
    }

}
