package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.answer.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.answer.NewAnswerDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/all/{id}")
    public List<AnswerDTO> getAllAnswers(@PathVariable int id) {
        return answerService.getAllAnswers(id);
    }

    @GetMapping("/{id}")
    public AnswerDTO getAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping("/")
    public AnswerDTO addNewAnswer(@RequestBody NewAnswerDTO answer) {
        return answerService.addNewAnswer(answer);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAnswerById(@PathVariable int id) {
        return answerService.deleteAnswerById(id);
    }
}
