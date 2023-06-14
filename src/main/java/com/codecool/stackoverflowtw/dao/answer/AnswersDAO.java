package com.codecool.stackoverflowtw.dao.answer;

import com.codecool.stackoverflowtw.controller.dto.answer.AnswerDTO;

import java.util.List;

public interface AnswersDAO {
    void sayHi();
    List<AnswerDTO> findAllByQuestionId(int id);
    AnswerDTO findOneById(int id);
    boolean save(AnswerDTO answerDTO);
    boolean findOneAndDelete(int id);
}
