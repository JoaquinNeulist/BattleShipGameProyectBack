package com.Battleship.Game.services;

import com.Battleship.Game.dtos.AccountDTO;
import com.Battleship.Game.models.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {

    List<AccountDTO> getListAccountDTO();

    AccountDTO getAccountDTO(Account account);

    Account findById(Long id);

    Account findByEmail(String email);

    Boolean existsByEmail(String email);

    void saveUser(Account account);

    List <AccountDTO> getTop10ScoringUsers(int limit);

}
