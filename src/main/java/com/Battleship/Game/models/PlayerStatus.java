package com.Battleship.Game.models;

public enum PlayerStatus {
    WAITING_FOR_OPPONENT, // Esperando a que otro jugador se una a la partida
    PLACING_SHIPS,        // Colocando los barcos en el tablero
    READY,                // Listo para comenzar el juego
    ATTACKING,            // Es el turno del jugador para atacar
    WAITING,              // Esperando el turno del oponente
    HIT,                  // El jugador ha sido golpeado
    MISS,                 // El jugador ha fallado su ataque
    WIN,                  // El jugador ha ganado el juego
    LOSE,                 // El jugador ha perdido el juego
    DISCONNECTED
}
