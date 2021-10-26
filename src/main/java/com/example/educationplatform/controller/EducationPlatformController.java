package com.example.educationplatform.controller;

import com.example.educationplatform.module.Courses;
import com.example.educationplatform.service.EducationPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/edu-platform")
public class EducationPlatformController {
    @Autowired
    EducationPlatformService educationPlatformService;

    @GetMapping("/student-courses/{studentId}")
    public ResponseEntity<?> getStudentCourses(@PathVariable Long studentId) {
        return ResponseEntity.ok(educationPlatformService.getStudentCourses(studentId));
    }
}
