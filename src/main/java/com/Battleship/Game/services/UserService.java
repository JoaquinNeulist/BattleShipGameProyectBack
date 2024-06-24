package com.Battleship.Game.services;

import com.Battleship.Game.dtos.UserDTO;
import com.Battleship.Game.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<UserDTO> getListUserDTO();

    UserDTO getUserDTO(User user);

    User findById(Long id);

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    void saveUser(User user);

}
