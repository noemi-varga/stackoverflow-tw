package com.codecool.stackoverflowtw.dao.answer;

import com.codecool.stackoverflowtw.controller.dto.answer.AnswerDTO;
import com.codecool.stackoverflowtw.dao.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswersDaoJdbc implements AnswersDAO{

    @Override
    public void sayHi() { System.out.println("Hi DAO!"); }

    private final Database database;

    public AnswersDaoJdbc(Database database) {
        this.database = database;
    }

    @Override
    public List<AnswerDTO> findAllByQuestionId(int id) {
        String template = """
        SELECT answer.answer_id, answer.question_id, answer.user_id, answer.date
        FROM answer WHERE question_id = ?
        ORDER BY date DESC
        """;
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            List<AnswerDTO> attempts = new ArrayList<>();
            while (resultSet.next()) {
                AnswerDTO attempt = toEntity(resultSet);
                attempts.add(attempt);
            }
            return attempts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AnswerDTO findOneById(int id) {
        String template = """
        SELECT answer.answer_id, answer.question_id, answer.user_id, answer.date
        FROM answer WHERE answer_id = ?
        """;
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            return toEntity(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(AnswerDTO answerDTO) {
        String template = "INSERT INTO answer (answer_detail, question_id, user_id) VALUES (?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(answerDTO, statement);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean findOneAndDelete(int id) {
        String template = "DELETE FROM answer WHERE question_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private AnswerDTO toEntity(ResultSet resultSet) throws SQLException {
        return new AnswerDTO(
                resultSet.getInt("answer_id"),
                resultSet.getString("answer_detail"),
                resultSet.getInt("question_id"),
                resultSet.getInt("user_id"),
                resultSet.getTimestamp("date")
        );
    }

    private void prepare(AnswerDTO answerDTO, PreparedStatement statement) throws SQLException {
        statement.setString(1, answerDTO.answer_detail());
        statement.setInt(2, answerDTO.question_id());
        statement.setInt(3, answerDTO.user_id());
    }
}
