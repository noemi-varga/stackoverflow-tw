package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.question.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.question.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        questionsDAO.sayHi();
        return questionsDAO.findAll();
    }

    public QuestionDTO getQuestionById(int id) {
        questionsDAO.sayHi();
        return questionsDAO.findOneById(id);
    }

    public boolean deleteQuestionById(int id) {
        questionsDAO.sayHi();
        return questionsDAO.findOneAndDelete(id);
    }

    public boolean addNewQuestion(QuestionDTO question) {
        questionsDAO.sayHi();
        return questionsDAO.save(question);
    }
}
