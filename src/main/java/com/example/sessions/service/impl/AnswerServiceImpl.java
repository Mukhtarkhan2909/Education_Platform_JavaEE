package com.example.sessions.service.impl;

import com.example.sessions.module.Answer;
import com.example.sessions.repository.AnswerRepository;
import com.example.sessions.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> getAnswer(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public Optional<Answer> getUserAnswers(Long userId, Long taskId) {
        return answerRepository.findByUserIdAndTaskId(userId, taskId);

    }

    @Override
    public List<Answer> getTaskAnswers(Long taskId) {
        return answerRepository.findByTaskId(taskId);
    }
}
