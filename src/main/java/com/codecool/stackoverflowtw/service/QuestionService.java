package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionsDAO.findAll();
    }

    public QuestionDTO getQuestionById(int id) {
        return questionsDAO.findOneById(id);
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.findOneAndDelete(id);
    }

    public QuestionDTO addNewQuestion(NewQuestionDTO question) {
        int id = questionsDAO.save(question);
        return getQuestionById(id);
    }

    public List<QuestionDTO> sortByTitle(String order) {
        return questionsDAO.sortByTitle(order);
    }

    public List<QuestionDTO> sortByAnswerCount(String order) {
        return questionsDAO.sortByAnswerCount(order);
    }

    public List<QuestionDTO> sortByDate(String order) {
        return questionsDAO.sortByDate(order);
    }
}
