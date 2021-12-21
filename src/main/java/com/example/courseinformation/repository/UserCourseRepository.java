package com.example.courseinformation.repository;

import com.example.courseinformation.module.Courses;
import com.example.courseinformation.module.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    List<UserCourse> findByUserId(Long userId);
}
