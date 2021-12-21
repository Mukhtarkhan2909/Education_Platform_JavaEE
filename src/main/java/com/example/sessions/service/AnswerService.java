package com.example.sessions.service;

import com.example.sessions.module.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    List<Answer> getAllAnswers();
    Optional<Answer> getAnswer(Long id);
    Optional<Answer> getUserAnswers(Long userId, Long taskId);
    List<Answer> getTaskAnswers(Long taskId);
}
