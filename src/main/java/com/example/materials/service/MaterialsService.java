package com.example.materials.service;

import com.example.materials.module.StudentMaterials;
import com.example.materials.module.TeacherMaterials;

public interface MaterialsService {
    StudentMaterials getStudentMaterialById(Long studentId);
    TeacherMaterials getTeacherMaterialById(Long teacherId);
}
