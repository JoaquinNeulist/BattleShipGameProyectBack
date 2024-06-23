package com.Battleship.Game.dtos;

import com.Battleship.Game.models.Shoot;
import com.Battleship.Game.models.ShootResult;

public class ShootDTO {
    private long id;
    private int cordX;
    private int cordY;
    private ShootResult result;

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

    public int getCordY() {
        return cordY;
    }

    public ShootResult getResult() {
        return result;
    }

    public int getCordX() {
        return cordX;
    }
}
