package com.example.materials.service.impl;

import com.example.materials.module.Materials;
import com.example.materials.module.StudentMaterials;
import com.example.materials.module.TeacherMaterials;
import com.example.materials.service.MaterialsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MaterialsServiceImpl implements MaterialsService {
    @Override
    public StudentMaterials getStudentMaterialById(Long studentId) {
        Materials materials = new Materials(1L, 1L, "Material1", "Content1");
        return new StudentMaterials(1L, Collections.singletonList(materials));
    }

    @Override
    public TeacherMaterials getTeacherMaterialById(Long teacherId) {
        Materials materials = new Materials(1L, 1L, "Material1", "Content1");
        return new TeacherMaterials(1L, Collections.singletonList(materials));
    }
}
