package com.Battleship.Game.controllers;

import com.Battleship.Game.dtos.AccountDTO;
import com.Battleship.Game.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/user")
    public ResponseEntity<List<AccountDTO>> getUsers() {
        List<AccountDTO> accountDTOS = accountService.getListAccountDTO();
        return ResponseEntity.ok().body(accountDTOS);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<AccountDTO>> getTop10Ranking() {
        List<AccountDTO> top10Accounts = accountService.getTop10ScoringUsers(10);
        return ResponseEntity.ok().body(top10Accounts);
    }


}
