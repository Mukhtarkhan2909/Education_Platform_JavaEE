package com.example.studentinformation.service.impl;

import com.example.studentinformation.module.Courses;
import com.example.studentinformation.module.Students;
import com.example.studentinformation.service.StudentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentInformationServiceImpl implements StudentInformationService {
    @Override
    public Students getStudentInformationById(Long id) {
        System.out.println("StudentInformationServiceImpl.getStudentInformationById");
        System.out.println("id = " + id);
        Students student = new Students();
        student.setId(id);
        return student;
    }

    @Override
    public List<Courses> getAllCourses() {
        return null;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Courses getCourseById(Long id) {
        return restTemplate.getForObject("http://localhost:8082/course/" + id, Courses.class);
    }
}
