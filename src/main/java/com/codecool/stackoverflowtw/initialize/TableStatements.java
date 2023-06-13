package com.codecool.stackoverflowtw.initialize;

public interface TableStatements {
    String QUESTION = """
            DROP TABLE IF EXISTS question;
            
            CREATE TABLE question (
            question_id SERIAL PRIMARY KEY,
            question_detail varchar(255) NOT NULL,
            user_id INTEGER,
            date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)
            """;
    String ANSWER= """
            DROP TABLE IF EXISTS answer;
            
            CREATE TABLE answer (
            answer_id SERIAL PRIMARY KEY,
            answer_detail varchar(255),
            question_id INTEGER,
            user_id INTEGER,
            date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)
            """;


    String USER= """
            DROP TABLE IF EXISTS "user";
            
            CREATE TABLE "user" (
            user_id SERIAL PRIMARY KEY,
            user_name varchar(255) NOT NULL,
            UNIQUE(user_name))
            """;


}
