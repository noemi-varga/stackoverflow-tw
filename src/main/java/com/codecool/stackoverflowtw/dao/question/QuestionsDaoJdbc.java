package com.codecool.stackoverflowtw.dao.question;

import com.codecool.stackoverflowtw.controller.dto.question.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.question.QuestionDTO;
import com.codecool.stackoverflowtw.dao.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class QuestionsDaoJdbc implements QuestionsDAO {
    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }

    private final Database database;

    public QuestionsDaoJdbc(Database database) {
        this.database = database;
    }

    @Override
    public List<QuestionDTO> findAll() {
        String template = "SELECT * FROM question";
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            List<QuestionDTO> attempts = new ArrayList<>();
            while (resultSet.next()) {
                QuestionDTO attempt = toEntity(resultSet);
                attempts.add(attempt);
            }
            return attempts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<QuestionDTO> sortByTitle(String order) {
        String template;
        if (order.equals("asc")) {
            template = """
                    SELECT question.question_id, question.question_title, question.question_detail, question.user_id, question.date
                    FROM question
                    ORDER BY question_title ASC
                    """;
        } else {
            template = """
                    SELECT question.question_id, question.question_title, question.question_detail, question.user_id, question.date
                    FROM question
                    ORDER BY question_title DESC
                    """;
        }
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            List<QuestionDTO> attempts = new ArrayList<>();
            while (resultSet.next()) {
                QuestionDTO attempt = toEntity(resultSet);
                attempts.add(attempt);
            }
            return attempts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<QuestionDTO> sortByDate(String order) {
        String template;
        if (order.equals("asc")) {
            template = """
                    SELECT question.question_id, question.question_title, question.question_detail, question.user_id, question.date
                    FROM question
                    ORDER BY question_date ASC
                    """;
        } else {
            template = """
                    SELECT question.question_id, question.question_title, question.question_detail, question.user_id, question.date
                    FROM question
                    ORDER BY question_date DESC
                    """;
        }
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            List<QuestionDTO> attempts = new ArrayList<>();
            while (resultSet.next()) {
                QuestionDTO attempt = toEntity(resultSet);
                attempts.add(attempt);
            }
            return attempts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<QuestionDTO> sortByAnswerCount(String order) {
        String template;
        System.out.println(order);
        if (order.equalsIgnoreCase("asc")) {
            template = """    
                    SELECT question.question_id, question.question_title, question.question_detail, question.user_id, question.date
                    FROM question
                    JOIN answer ON answer.question_id = question.question_id
                    GROUP BY question.question_id
                    ORDER BY COUNT(answer.answer_id) ASC
                    """;
        } else {
            template = """
                    SELECT question.question_id, question.question_title, question.question_detail, question.user_id, question.date
                    FROM question
                    JOIN answer ON answer.question_id = question.question_id
                    GROUP BY question.question_id
                    ORDER BY COUNT(answer.answer_id) DESC
                    """;
        }
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(template)) {
            List<QuestionDTO> attempts = new ArrayList<>();
            while (resultSet.next()) {
                QuestionDTO attempt = toEntity(resultSet);
                attempts.add(attempt);
            }
            return attempts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public QuestionDTO findOneById(int id) {
        String template = "SELECT * FROM question WHERE question_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setInt(1, id);
            QuestionDTO question = null;
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    question = toEntity(resultSet);
                }
            }
            return question;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(NewQuestionDTO questionDTO) {
        String template = "INSERT INTO question (question_title, question_detail, user_id) VALUES (?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template, Statement.RETURN_GENERATED_KEYS)) {
            prepare(questionDTO, statement);
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
        String template = "DELETE FROM question WHERE question_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private QuestionDTO toEntity(ResultSet resultSet) throws SQLException {
        return new QuestionDTO(
                resultSet.getInt("question_id"),
                resultSet.getString("question_title"),
                resultSet.getString("question_detail"),
                resultSet.getInt("user_id"),
                resultSet.getTimestamp("date")
        );
    }

    private void prepare(NewQuestionDTO questionsDTO, PreparedStatement statement) throws SQLException {
        statement.setString(1, questionsDTO.question_title());
        statement.setString(2, questionsDTO.question_detail());
        statement.setInt(3, questionsDTO.user_id());
    }


}
