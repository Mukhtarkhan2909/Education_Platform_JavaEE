package com.example.materials.repository;

import com.example.materials.module.Material;
import com.example.materials.module.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndCourseId(Long userId, Long courseId);
    List<Task> findByCourseId(Long courseId);
}