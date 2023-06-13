package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();
    List<QuestionDTO> findAll();
    QuestionDTO findOneById(int id);
    boolean save(QuestionDTO questionDTO);
    boolean findOneAndDelete(int id);
}
