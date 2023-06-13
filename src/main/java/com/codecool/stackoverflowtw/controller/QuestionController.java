package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/sort/title")
    public List<QuestionDTO> sortByTitle() {
        return questionService.sortByTitle();
    }

    @GetMapping("/sort/answerCount")
    public List<QuestionDTO> sortByAnswerCount() {
        return questionService.sortByAnswerCount();
    }

    @GetMapping("/sort/date")
    public List<QuestionDTO> sortByDate() {
        return questionService.sortByDate();
    }

    @PostMapping("/")
    public boolean addNewQuestion(@RequestBody NewQuestionDTO question) {
        //TODO return posted record!
        return questionService.addNewQuestion(question);
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return questionService.deleteQuestionById(id);
    }
}
