package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.answer.AnswerDTO;
import com.codecool.stackoverflowtw.dao.answer.AnswersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }

    public List<AnswerDTO> getAllAnswers(int id) {
        answersDAO.sayHi();
        return answersDAO.findAllByQuestionId(id);
    }

    public AnswerDTO getAnswerById(int id) {
        answersDAO.sayHi();
        return answersDAO.findOneById(id);
    }

    public boolean deleteAnswerById(int id) {
        answersDAO.sayHi();
        return answersDAO.findOneAndDelete(id);
    }

    public boolean addNewAnswer(AnswerDTO answer) {
        answersDAO.sayHi();
        return answersDAO.save(answer);
    }
}

