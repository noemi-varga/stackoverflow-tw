package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.AnswersDaoJdbc;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.initialize.TableInitializer;
import com.codecool.stackoverflowtw.initialize.TableStatements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class StackoverflowTwApplication {

    public static void main(String[] args) {

        //String username = System.getenv("USER_NAME");

        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    @Bean
    public QuestionsDAO questionsDAO(Database database) {
        return new QuestionsDaoJdbc(database);
    }

    @Bean
    public AnswersDAO answersDAO(Database database) {
        return new AnswersDaoJdbc(database);
    }

    @Bean
    public Database database() {
        Database database = new Database(
                "jdbc:postgresql://localhost:5432/stackoverflow",
                "postgres",
                "");
        Map<String, String> tables = Map.of(
                "question", TableStatements.QUESTION,
                "answer", TableStatements.ANSWER,
                "user", TableStatements.USER
        );
        TableInitializer tableInitializer = new TableInitializer(database, tables);
        tableInitializer.initialize();
        return database;
    }
}
