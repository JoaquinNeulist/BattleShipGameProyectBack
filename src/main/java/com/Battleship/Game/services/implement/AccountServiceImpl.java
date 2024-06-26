package com.Battleship.Game.services.implement;

import com.Battleship.Game.dtos.AccountDTO;
import com.Battleship.Game.models.Account;
import com.Battleship.Game.repositories.AccountRepository;
import com.Battleship.Game.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> getListAccountDTO() {
        return accountRepository.findAll()
                .stream()
                .map(user -> new AccountDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccountDTO(Account account) {
        return new AccountDTO(account);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public void saveUser(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<AccountDTO> getTop10ScoringUsers(int limit) {
            return getListAccountDTO()
                    .stream().sorted(Comparator.comparingInt(AccountDTO::getScore).reversed())
                    .limit(11)
                    .collect(Collectors.toList());
        }
    }





