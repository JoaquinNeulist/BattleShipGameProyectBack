package com.Battleship.Game.controllers;

import com.Battleship.Game.dtos.MatchDTO;
import com.Battleship.Game.models.Account;
import com.Battleship.Game.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/create")
    public ResponseEntity<?> createMatch(Authentication authentication){
        return new ResponseEntity<>(matchService.createMatch(authentication), HttpStatus.CREATED);
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinMatch(Authentication authentication, @RequestBody Map<String, String> request) {
        String partyCode = request.get("partyCode");
        if (partyCode == null || partyCode.isEmpty()) {
            return new ResponseEntity<>("Party code is required", HttpStatus.BAD_REQUEST);
        }
        try {
            MatchDTO matchDTO = new MatchDTO(matchService.joinMatch(authentication, partyCode));
            return new ResponseEntity<>(matchDTO, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentMatch(Authentication authentication) {
        return new ResponseEntity<>(matchService.getCurrentMatch(authentication), HttpStatus.OK);
    }
}
