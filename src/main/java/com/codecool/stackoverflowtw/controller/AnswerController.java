package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/all")
    public List<AnswerDTO> getAllAnswers() {
        return List.of();
    }

    @GetMapping("/{id}")
    public AnswerDTO getAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping("/")
    public boolean addNewAnswer(@RequestBody NewAnswerDTO answer) {
        return false;
    }

    @DeleteMapping("/{id}")
    public boolean deleteAnswerById(@PathVariable int id) {
        return answerService.deleteAnswerById(id);
    }
}