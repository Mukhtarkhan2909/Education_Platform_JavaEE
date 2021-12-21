package com.example.sessions.repository;

import com.example.sessions.module.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByTaskId(Long taskId);
    Optional<Answer> findByUserIdAndTaskId(Long userId, Long taskId);
}
