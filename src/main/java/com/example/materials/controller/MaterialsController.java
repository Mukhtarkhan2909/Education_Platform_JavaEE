package com.example.materials.controller;

import com.example.materials.service.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("materials")
public class MaterialsController {
    @Autowired
    MaterialsService materialsService;

    @GetMapping("/student-materials/{studentId}")
    public ResponseEntity<?> getStudentMaterialById(@PathVariable Long studentId) {
        return ResponseEntity.ok(materialsService.getStudentMaterialById(studentId));
    }

    @GetMapping("/teacher-materials/{teacherId}")
    public ResponseEntity<?> getTeacherMaterialById(@PathVariable Long teacherId) {
        return ResponseEntity.ok(materialsService.getTeacherMaterialById(teacherId));
    }
}
