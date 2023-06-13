package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();
    List<QuestionDTO> findAll();
    QuestionDTO findOneById(int id);
    void save(QuestionDTO questionDTO);
    void findOneAndDelete(int id);
}
