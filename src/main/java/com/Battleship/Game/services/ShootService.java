package com.Battleship.Game.services;

import com.Battleship.Game.dtos.ShootRequest;
import com.Battleship.Game.models.Shoot;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface ShootService {

    void saveShoot(Shoot shoot);

    ResponseEntity<?> generateShoot(Long boardId, ShootRequest shootRequest, Authentication authentication);
}
