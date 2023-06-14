package com.codecool.stackoverflowtw.dao.user;

import com.codecool.stackoverflowtw.controller.dto.answer.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.user.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.user.UserDTO;
import com.codecool.stackoverflowtw.dao.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UsersDaoJdbc implements UsersDAO{

    @Override
    public void sayHi() { System.out.println("Hi DAO!"); }

    private final Database database;

    public UsersDaoJdbc(Database database) {
        this.database = database;
    }

    @Override
    public List<UserDTO> findAll() {
        String template = """
            SELECT * FROM "user"
        """;
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            List<UserDTO> users = new ArrayList<>();
            while (resultSet.next()) {
                UserDTO user = toEntity(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO findOneByUsername(String username) {
        String template = """
            SELECT * FROM "user" WHERE user_name = ?
        """;
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1, username);
            UserDTO user = null;
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    user = toEntity(resultSet);
                }
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO findOneById(int id) {
        String template = """
            SELECT * FROM "user" WHERE user_id = ?
        """;
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            UserDTO user = null;
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    user = toEntity(resultSet);
                }
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(NewUserDTO userDTO) {
        String template = """
            INSERT INTO "user" (user_name, password) VALUES (?, ?)
        """;
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template, Statement.RETURN_GENERATED_KEYS)) {
            prepare(userDTO, statement);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else throw new NoSuchElementException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UserDTO toEntity(ResultSet resultSet) throws SQLException {
        return new UserDTO(
                resultSet.getInt("user_id"),
                resultSet.getString("user_name"),
                resultSet.getString("password")
        );
    }

    private void prepare(NewUserDTO usersDTO, PreparedStatement statement) throws SQLException {
        statement.setString(1, usersDTO.user_name());
        statement.setString(2, usersDTO.password());
    }
}
