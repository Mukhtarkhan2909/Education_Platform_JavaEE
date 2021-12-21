package com.example.courseinformation.service.impl;

import com.example.courseinformation.module.Courses;
import com.example.courseinformation.module.UserCourse;
import com.example.courseinformation.repository.CourseRepository;
import com.example.courseinformation.repository.UserCourseRepository;
import com.example.courseinformation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserCourseRepository userCourseRepository;

    @Override
    public List<UserCourse> getUserCourses(Long userId) {
        return userCourseRepository.findByUserId(userId);
    }

    @Override
    public Optional<Courses> getCourseInformation(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }
}
