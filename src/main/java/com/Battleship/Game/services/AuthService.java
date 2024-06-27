package com.Battleship.Game.services;

import com.Battleship.Game.dtos.LoginDTO;
import com.Battleship.Game.dtos.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<?> login(LoginDTO loginDTO);
    ResponseEntity<?> register(RegisterDTO registerDTO);
    ResponseEntity<?> getCurrentUser(Authentication authentication);
}
