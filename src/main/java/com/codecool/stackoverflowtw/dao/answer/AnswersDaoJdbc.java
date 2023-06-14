package com.codecool.stackoverflowtw.dao.answer;

import com.codecool.stackoverflowtw.controller.dto.answer.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.answer.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.question.QuestionDTO;
import com.codecool.stackoverflowtw.dao.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AnswersDaoJdbc implements AnswersDAO{

    @Override
    public void sayHi() { System.out.println("Hi DAO!"); }

    private final Database database;

    public AnswersDaoJdbc(Database database) {
        this.database = database;
    }

    // TODO fix this idk
    @Override
    public List<AnswerDTO> findAllByQuestionId(int id) {
        System.out.println(id);
        String template = """
        SELECT answer.answer_id, answer.answer_detail, answer.question_id, answer.user_id, answer.date
        FROM answer WHERE answer.question_id = ?
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
        SELECT answer.answer_id, answer.answer_detail, answer.question_id, answer.user_id, answer.date
        FROM answer WHERE answer_id = ?
        """;
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            AnswerDTO answer = null;
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    answer = toEntity(resultSet);
                }
            }
            return answer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(NewAnswerDTO answerDTO) {
        String template = "INSERT INTO answer (answer_detail, question_id, user_id) VALUES (?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template, Statement.RETURN_GENERATED_KEYS)) {
            prepare(answerDTO, statement);
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

    private void prepare(NewAnswerDTO answerDTO, PreparedStatement statement) throws SQLException {
        statement.setString(1, answerDTO.answer_detail());
        statement.setInt(2, answerDTO.question_id());
        statement.setInt(3, answerDTO.user_id());
    }
}
