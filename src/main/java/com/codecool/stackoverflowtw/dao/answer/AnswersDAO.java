package com.codecool.stackoverflowtw.dao.answer;

import com.codecool.stackoverflowtw.controller.dto.answer.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.answer.NewAnswerDTO;

import java.util.List;

public interface AnswersDAO {
    void sayHi();
    List<AnswerDTO> findAllByQuestionId(int id);
    AnswerDTO findOneById(int id);
    int save(NewAnswerDTO answerDTO);
    boolean findOneAndDelete(int id);
}
