package com.example.teacherinformation.controller;

import com.example.teacherinformation.service.TeacherInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherInformationService teacherInformationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherInformationById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherInformationService.getTeacherInformationById(id));
    }

}
