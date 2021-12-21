package com.example.materials.service;

import com.example.materials.module.Material;
import com.example.materials.module.Task;

import java.util.List;
import java.util.Optional;

public interface MaterialsService {
    Optional<Material> getMaterialById(Long id);
    List<Material> getUserMaterials(Long userId, Long courseId);
    List<Material> getAllMaterials();
    List<Material> getCourseMaterials(Long courseId);
    Optional<Task> getTaskById(Long id);
    List<Task> getUserTasks(Long userId, Long courseId);
    List<Task> getAllTasks();
    List<Task> getCourseTasks(Long courseId);
}