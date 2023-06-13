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

    public boolean addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.save(question);
    }

    public List<QuestionDTO> sortByTitle() {
        return questionsDAO.sortByTitle();
    }

    public List<QuestionDTO> sortByAnswerCount() {
        return questionsDAO.sortByAnswerCount();
    }

    public List<QuestionDTO> sortByDate() {
        return questionsDAO.sortByDate();
    }
}
