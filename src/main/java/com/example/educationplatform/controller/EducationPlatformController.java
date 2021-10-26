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

    @GetMapping("/teacher-courses/{teacherId}")
    public ResponseEntity<?> getTeacherCourses(@PathVariable Long teacherId) {
        return ResponseEntity.ok(educationPlatformService.getTeacherCourses(teacherId));
    }

    @GetMapping("/student-information/{studentId}")
    public ResponseEntity<?> getStudentInformationById(@PathVariable Long studentId) {
        return ResponseEntity.ok(educationPlatformService.getStudentInformationById(studentId));
    }

    @GetMapping("/teacher-information/{teacherId}")
    public ResponseEntity<?> getTeacherInformationById(@PathVariable Long teacherId) {
        return ResponseEntity.ok(educationPlatformService.getTeacherInformationById(teacherId));
    }

    @GetMapping("/student-materials/{studentId}")
    public ResponseEntity<?> getStudentMaterial(@PathVariable Long studentId) {
        return ResponseEntity.ok(educationPlatformService.getStudentMaterial(studentId));
    }

    @GetMapping("/teacher-materials/{teacherId}")
    public ResponseEntity<?> getTeacherMaterial(@PathVariable Long teacherId) {
        return ResponseEntity.ok(educationPlatformService.getTeacherMaterial(teacherId));
    }

    @GetMapping("/student-sessions/{studentId}")
    public ResponseEntity<?> getStudentSessions(@PathVariable Long studentId) {
        return ResponseEntity.ok(educationPlatformService.getStudentSessions(studentId));
    }

}
