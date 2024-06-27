package com.Battleship.Game.repositories;

import com.Battleship.Game.models.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
}
