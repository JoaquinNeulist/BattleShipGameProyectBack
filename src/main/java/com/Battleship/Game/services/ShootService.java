package com.Battleship.Game.services;

import com.Battleship.Game.models.Shoot;
import org.springframework.stereotype.Service;

@Service
public interface ShootService {

    void saveShoot(Shoot shoot);


}
