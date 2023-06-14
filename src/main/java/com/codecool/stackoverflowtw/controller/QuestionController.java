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

    @GetMapping("/sort/title?sort={order}")
    public List<QuestionDTO> sortByTitle(@PathVariable String order) {
        return questionService.sortByTitle(order);
    }

    @GetMapping("/sort/answerCount?sort={order}")
    public List<QuestionDTO> sortByAnswerCount(@PathVariable String order) {
        return questionService.sortByAnswerCount(order);
    }

    @GetMapping("/sort/date?sort={order}")
    public List<QuestionDTO> sortByDate(@PathVariable String order) {
        return questionService.sortByDate(order);
    }

    @PostMapping("/")
    public QuestionDTO addNewQuestion(@RequestBody NewQuestionDTO question) {
        //TODO return posted record!
        return questionService.addNewQuestion(question);
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return questionService.deleteQuestionById(id);
    }
}
