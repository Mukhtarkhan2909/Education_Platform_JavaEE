package com.example.materials.service.impl;

import com.example.materials.module.Courses;
import com.example.materials.module.Materials;
import com.example.materials.module.StudentMaterials;
import com.example.materials.module.TeacherMaterials;
import com.example.materials.service.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class MaterialsServiceImpl implements MaterialsService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public StudentMaterials getStudentMaterialById(Long studentId) {
        Materials materials = new Materials();
        materials.setId(1L);
        materials.setCourse(restTemplate.getForObject("http://localhost:8085/courses/get-course/" + 1, Courses.class));
        materials.setName("Project");
        return new StudentMaterials(studentId, Collections.singletonList(materials));
    }

    @Override
    public TeacherMaterials getTeacherMaterialById(Long teacherId) {
        Materials materials = new Materials();
        materials.setId(1L);
        materials.setCourse(restTemplate.getForObject("http://localhost:8085/courses/get-course/" + 1, Courses.class));
        materials.setName("Lecture");
        return new TeacherMaterials(1L, Collections.singletonList(materials));
    }
}