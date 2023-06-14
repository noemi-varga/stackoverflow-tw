package com.codecool.stackoverflowtw.controller.dto.question;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record QuestionDTO(int question_id, String question_title, String question_detail, int user_id, Timestamp date) {}


