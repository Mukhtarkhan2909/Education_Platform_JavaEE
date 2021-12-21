package com.example.materials.service.impl;

import com.example.materials.module.Material;
import com.example.materials.module.Task;
import com.example.materials.repository.MaterialRepository;
import com.example.materials.repository.TaskRepository;
import com.example.materials.service.MaterialsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialsServiceImpl implements MaterialsService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Optional<Material> getMaterialById(Long id) {
        return materialRepository.findById(id);
    }

    @Override
    public List<Material> getUserMaterials(Long userId, Long courseId) {
        return materialRepository.findByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public List<Material> getCourseMaterials(Long courseId) {
        return materialRepository.findByCourseId(courseId);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getUserTasks(Long userId, Long courseId) {
        return taskRepository.findByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getCourseTasks(Long courseId) {
        return taskRepository.findByCourseId(courseId);
    }

}