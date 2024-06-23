package com.Battleship.Game.services.securityServices;

import com.Battleship.Game.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
        com.Battleship.Game.models.User user = userService.findByEmail(username);
        if (user == null){
            throw  new UsernameNotFoundException(username);
        }
        return User
                .withUsername(username)
                .password(user.getPassword())
                .roles("CLIENT")
                .build();
    }

}
