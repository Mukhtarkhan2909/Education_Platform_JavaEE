package com.example.educationplatform.service;

import com.example.educationplatform.module.*;

import java.util.List;

public interface EducationPlatformService {
    List<Courses> getStudentCourses(Long studentId);
    List<Courses> getTeacherCourses(Long teacherId);
    Students getStudentInformationById(Long id);
    StudentSessions getStudentSessions(Long studentID);
    StudentMaterials getStudentMaterialById(Long studentId);
    TeacherMaterials getTeacherMaterialById(Long teacherId);
    Teachers getTeacherInformationById(Long id);
}
