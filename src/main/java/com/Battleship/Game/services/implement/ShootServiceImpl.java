package com.Battleship.Game.services.implement;

import com.Battleship.Game.models.Shoot;
import com.Battleship.Game.repositories.ShootRepository;
import com.Battleship.Game.services.ShootService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShootServiceImpl implements ShootService {
    @Autowired
    private ShootRepository shootRepository;

    @Override
    public void saveShoot(Shoot shoot) {
         shootRepository.save(shoot);
    }
}
