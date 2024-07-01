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
			Account account1 = new Account("Test@mail.com", "joaco", "neulist", "Jota", passwordEncoder.encode("123"), 15);
			accountRepository.save(account1);
			System.out.println(account1);
			Account account2 = new Account("Test@mail2.com", "Agus", "Russo", "AgusR", passwordEncoder.encode("123"), 1);
      account2.setAdmin(true);
			accountRepository.save(account2);
			Account account3 = new Account("Test@mail3.com", "Mayco", "Dominguez", "Mayk", passwordEncoder.encode("123"), 2);
			accountRepository.save(account3);
			Account account4 = new Account("Test@mail4.com", "Chris", "Vega", "Kryzpo", passwordEncoder.encode("123"), 15);
			accountRepository.save(account4);
			// Agregar más usuarios para pruebas
			Account account5 = new Account("Test@mail5.com", "Edmundo", "Ruveda", "Edmun", passwordEncoder.encode("123"), 4);
			accountRepository.save(account5);

			Account account6 = new Account("Test@mail6.com", "Mariela", "Morsucci", "Mari", passwordEncoder.encode("123"), 5);
			accountRepository.save(account6);

			Account account7 = new Account("Test@mail7.com", "Diego", "Vallejo", "Dieguito", passwordEncoder.encode("123"), 6);
			accountRepository.save(account7);

			Account account8 = new Account("Test@mail8.com", "Pedro", "Sanabria", "Pedrito", passwordEncoder.encode("123"), 7);
			accountRepository.save(account8);

			Account account9 = new Account("Test@mail9.com", "Jelena", "Palavecino", "Jele", passwordEncoder.encode("123"), 8);
			accountRepository.save(account9);

			Account account10 = new Account("Test@mail10.com", "Flor", "Ayala", "Flor", passwordEncoder.encode("123"), 9);
			accountRepository.save(account10);
		};
	}
}


