package com.Battleship.Game.repositories;

import com.Battleship.Game.dtos.AccountDTO;
import com.Battleship.Game.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername (String username);

    Account findByEmail (String email);

    Boolean existsByEmail (String email);

}
