package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;

public record AnswerDTO(int answer_id, String answer_detail, int question_id, int user_id, Timestamp date) {}
