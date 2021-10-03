package com.example.studentinformation.service;

import com.example.studentinformation.module.Courses;
import com.example.studentinformation.module.Students;

import java.util.Arrays;
import java.util.List;

public interface StudentInformationService {
    Students getStudentInformationById(Long id);
    List<Courses> getAllCourses();
    Courses getCourseById(Long id);
}
