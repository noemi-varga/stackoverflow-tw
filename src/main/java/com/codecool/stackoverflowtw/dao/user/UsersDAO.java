package com.codecool.stackoverflowtw.dao.user;

import com.codecool.stackoverflowtw.controller.dto.user.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.user.UserDTO;

import java.util.List;

public interface UsersDAO {
    void sayHi();
    List<UserDTO> findAll();
    UserDTO findOneByUsername(String username);
    UserDTO findOneById(int id);
    int save(NewUserDTO userDTO);
}
