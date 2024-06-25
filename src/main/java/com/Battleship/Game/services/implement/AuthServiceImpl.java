package com.Battleship.Game.services.implement;

import com.Battleship.Game.dtos.LoginDTO;
import com.Battleship.Game.dtos.RegisterDTO;
import com.Battleship.Game.dtos.AccountDTO;
import com.Battleship.Game.models.Account;
import com.Battleship.Game.services.AuthService;
import com.Battleship.Game.services.AccountService;
import com.Battleship.Game.services.securityServices.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
    public ResponseEntity<?> register(RegisterDTO registerDTO){
        if (registerDTO.fName().isBlank()){
            return new ResponseEntity<>("The first name field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.lName().isBlank()){
            return new ResponseEntity<>("The last name field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.email().isBlank()){
            return new ResponseEntity<>("The email field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.password().isBlank()){
            return new ResponseEntity<>("The password field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.username().isBlank()){
            return new ResponseEntity<>("The username field must not be empty", HttpStatus.FORBIDDEN);
        }
        if (registerDTO.password().length() < 8) {
            return new ResponseEntity<>("The password must have at least 8 characters", HttpStatus.BAD_REQUEST);
        }
        if (accountService.existsByEmail(registerDTO.email())){
            return new ResponseEntity<>("Email is already in use", HttpStatus.FORBIDDEN);
        }
        Account newAccount = new Account(registerDTO.email(), registerDTO.fName(), registerDTO.lName(), registerDTO.username(), passwordEncoder.encode(registerDTO.password()), 0);
        accountService.saveUser(newAccount);
        return new ResponseEntity<>("User created succesfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.email());
            final String jwt = jwtUtilService.generateToken(userDetails);
            return ResponseEntity.ok(jwt);
        }catch (Exception e){
            return new ResponseEntity<>("email or password invalid", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        Account account = accountService.findByEmail(authentication.getName());
        if(account != null){
            return ResponseEntity.ok(accountService.getAccountDTO(account));
        }else{
            throw new AccessDeniedException("User not found");
        }
    }
}
