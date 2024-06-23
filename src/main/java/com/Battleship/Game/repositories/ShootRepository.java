package com.Battleship.Game.repositories;

import com.Battleship.Game.models.Shoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShootRepository extends JpaRepository<Shoot, Long> {
}
