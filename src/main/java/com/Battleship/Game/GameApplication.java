package com.Battleship.Game;

import com.Battleship.Game.models.Account;
import com.Battleship.Game.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(
			AccountRepository accountRepository,
			MatchRepository matchRepository,
			PlayerMatchRepository playerMatchRepository,
			BoardRepository boardRepository,
			ShootRepository shootRepository,
			ShipRepository shipRepository
	){
		return args -> {
			Account account1 = new Account("Test@mail.com", "joaco", "neulist", "Jota", passwordEncoder.encode("123"));
			accountRepository.save(account1);
			System.out.println(account1);
			Account account2 = new Account("test@mail.com", "joaco2", "neulist", "Jota2", passwordEncoder.encode("456"));
			accountRepository.save(account2);
		};
	}
}


