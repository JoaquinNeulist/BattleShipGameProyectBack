package com.Battleship.Game.controllers;

import com.Battleship.Game.dtos.LoginDTO;
import com.Battleship.Game.dtos.RegisterDTO;
import com.Battleship.Game.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getAccount(Authentication authentication) {
        return authService.getCurrentUser(authentication);
    }
}
