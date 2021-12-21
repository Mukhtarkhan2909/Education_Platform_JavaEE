package com.example.courseinformation.repository;

import com.example.courseinformation.module.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Courses, Long> {
    Optional<Courses> findByName(String username);
}
