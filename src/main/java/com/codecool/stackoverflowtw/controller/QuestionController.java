package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.question.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.question.QuestionDTO;
import com.codecool.stackoverflowtw.service.QuestionService;
import jakarta.websocket.server.PathParam;
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
    public List<QuestionDTO> sortByTitle(@RequestParam String sort) {
        return questionService.sortByTitle(sort);
    }

    @GetMapping("/sort/answer")
    public List<QuestionDTO> sortByAnswerCount(@RequestParam String sort) {
        return questionService.sortByAnswerCount(sort);
    }

    @GetMapping("/sort/date")
    public List<QuestionDTO> sortByDate(@RequestParam String sort) {
        return questionService.sortByDate(sort);
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
