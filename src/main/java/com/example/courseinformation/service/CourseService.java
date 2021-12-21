package com.example.courseinformation.service;

import com.example.courseinformation.module.Courses;
import com.example.courseinformation.module.UserCourse;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<UserCourse> getUserCourses(Long userId);
    Optional<Courses> getCourseInformation(Long courseId);
    List<Courses> getAllCourses();
}
