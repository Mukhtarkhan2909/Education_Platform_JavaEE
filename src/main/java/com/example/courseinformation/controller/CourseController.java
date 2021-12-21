package com.example.courseinformation.controller;

import com.example.courseinformation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/get-courses")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/find-course/{courseId}")
    public ResponseEntity<?> getCourseInformation(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseInformation(courseId));
    }

    @GetMapping("/user-courses/{userId}")
    public ResponseEntity<?> getUserCourses(@PathVariable Long userId) {
        return ResponseEntity.ok(courseService.getUserCourses(userId));
    }
}
