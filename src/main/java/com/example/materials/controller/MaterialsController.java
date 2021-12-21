package com.example.materials.controller;

import com.example.materials.service.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materials")
public class MaterialsController {
    @Autowired
    MaterialsService materialsService;

    @GetMapping("/get-materials")
    public ResponseEntity<?> getMaterialById() {
        return ResponseEntity.ok(materialsService.getAllMaterials());
    }

    @GetMapping("/find-material/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable Long id) {
        return ResponseEntity.ok(materialsService.getMaterialById(id));
    }

    @GetMapping("/user-materials/{userId}/{courseId}")
    public ResponseEntity<?> getUserMaterials(@PathVariable Long userId, @PathVariable Long courseId) {
        return ResponseEntity.ok(materialsService.getUserMaterials(userId, courseId));
    }

    @GetMapping("/course-materials/{courseId}")
    public ResponseEntity<?> getCourseMaterials(@PathVariable Long courseId) {
        return ResponseEntity.ok(materialsService.getCourseMaterials(courseId));
    }

    @GetMapping("/get-tasks")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(materialsService.getAllTasks());
    }

    @GetMapping("/find-task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(materialsService.getTaskById(id));
    }

    @GetMapping("/user-tasks/{userId}/{courseId}")
    public ResponseEntity<?> getUserTasks(@PathVariable Long userId, @PathVariable Long courseId) {
        return ResponseEntity.ok(materialsService.getUserTasks(userId, courseId));
    }

    @GetMapping("/course-tasks/{courseId}")
    public ResponseEntity<?> getCourseTasks(@PathVariable Long courseId) {
        return ResponseEntity.ok(materialsService.getCourseTasks(courseId));
    }

}