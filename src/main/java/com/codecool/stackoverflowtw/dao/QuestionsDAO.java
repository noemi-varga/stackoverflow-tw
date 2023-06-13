package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();
    List<QuestionDTO> findAll();
    QuestionDTO findOneById(int id);
    boolean save(NewQuestionDTO questionDTO);
    boolean findOneAndDelete(int id);
    List<QuestionDTO> sortByTitle();
    List<QuestionDTO> sortByAnswerCount();
    List<QuestionDTO> sortByDate();

}
