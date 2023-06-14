package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
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
        return answersDAO.findAllByQuestionId(id);
    }

    public AnswerDTO getAnswerById(int id) {
        return answersDAO.findOneById(id);
    }

    public boolean deleteAnswerById(int id) {
        return answersDAO.findOneAndDelete(id);
    }

    public boolean addNewAnswer(AnswerDTO answer) {
        return answersDAO.save(answer);
    }
}

