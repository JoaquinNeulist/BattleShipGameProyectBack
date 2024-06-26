package com.Battleship.Game.services;

import com.Battleship.Game.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {

    ResponseEntity<?> createBoard(Long boardId, BoardRequest boardRequest, Authentication authentication);
}
