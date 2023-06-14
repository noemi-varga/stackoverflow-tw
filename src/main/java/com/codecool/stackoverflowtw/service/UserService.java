package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.user.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.user.UserDTO;
import com.codecool.stackoverflowtw.dao.user.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UsersDAO usersDAO;

    @Autowired
    public UserService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public List<UserDTO> getAllUsers() {
        return usersDAO.findAll();
    }

    public UserDTO getUserByUsername(String username) {
        return usersDAO.findOneByUsername(username);
    }

    public UserDTO getUserById(int id) {
        return usersDAO.findOneById(id);
    }

    public UserDTO addNewUser(NewUserDTO newUserDTO) {
        return usersDAO.findOneById(usersDAO.save(newUserDTO));
    }
}
