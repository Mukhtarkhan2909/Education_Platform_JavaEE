package com.example.studentinformation.controller;

import com.example.studentinformation.service.StudentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentInformationService studentInformationService;

    @GetMapping("/get-student/{id}")
    public ResponseEntity<?> getStudentInformationById(@PathVariable Long id) {
        return ResponseEntity.ok(studentInformationService.getStudentInformationById(id));
    }

}
