package com.example.studentinformation.controller;

import com.example.studentinformation.module.Courses;
import com.example.studentinformation.service.StudentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentInformationService studentInformationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentInformationById(@PathVariable Long id) {
        return ResponseEntity.ok(studentInformationService.getStudentInformationById(id));
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(studentInformationService.getAllCourses());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(studentInformationService.getCourseById(id));
    }


}
