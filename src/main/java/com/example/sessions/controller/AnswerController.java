package com.example.sessions.controller;

import com.example.sessions.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping("/get-answers")
    public ResponseEntity<?> getAllAnswers() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }
    @GetMapping("/find-answer/{id}")
    public ResponseEntity<?> getAnswer(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.getAnswer(id));
    }
    @GetMapping("/user-answers/{userId}/{taskId}")
    public ResponseEntity<?> getUserAnswers(@PathVariable Long userId, @PathVariable Long taskId) {
        return ResponseEntity.ok(answerService.getUserAnswers(userId, taskId));
    }
    @GetMapping("/task-answers/{taskId}")
    public ResponseEntity<?> getTaskAnswers(@PathVariable Long taskId) {
        return ResponseEntity.ok(answerService.getTaskAnswers(taskId));
    }
}
