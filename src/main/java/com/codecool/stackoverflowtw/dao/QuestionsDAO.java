package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();
    List<QuestionDTO> findAll();
    QuestionDTO findOneById(int id);
    int save(NewQuestionDTO questionDTO);
    boolean findOneAndDelete(int id);
    List<QuestionDTO> sortByTitle(String order);
    List<QuestionDTO> sortByAnswerCount(String order);
    List<QuestionDTO> sortByDate(String order);

}
