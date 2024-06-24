package com.Battleship.Game.services.securityServices;
import com.Battleship.Game.models.Account;
import com.Battleship.Game.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        if (account.isAdmin()){
            return User
                    .withUsername(username)
                    .password(account.getPassword())
                    .roles("ADMIN")
                    .build();
        }
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles("CLIENT")
                .build();
    }
}
