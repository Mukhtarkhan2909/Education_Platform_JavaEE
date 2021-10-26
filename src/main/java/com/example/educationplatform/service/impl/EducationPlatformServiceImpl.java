package com.example.educationplatform.service.impl;

import com.example.educationplatform.module.*;
import com.example.educationplatform.service.EducationPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationPlatformServiceImpl implements EducationPlatformService {

    @Autowired
    RestOperations restTemplate;

    @Override
    public List<Courses> getStudentCourses(Long studentId) {
        StudentCourses studentCourses = new StudentCourses();
        studentCourses.setStudentId(studentId);

        return studentCourses.getStudentCourses().stream()
                .map(course -> {
                    Courses courses = restTemplate.getForObject("http://course-information-service/student-courses/" + course.getId(), Courses.class);
                    return new Courses(course.getId(), courses.getName());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Courses> getTeacherCourses(Long teacherId) {
        return null;
    }

    @Override
    public Students getStudentInformationById(Long id) {
        return null;
    }

    @Override
    public StudentSessions getStudentSessions(Long studentID) {
        return null;
    }

    @Override
    public StudentMaterials getStudentMaterialById(Long studentId) {
        return null;
    }

    @Override
    public TeacherMaterials getTeacherMaterialById(Long teacherId) {
        return null;
    }

    @Override
    public Teachers getTeacherInformationById(Long id) {
        return null;
    }
}
