package com.example.educationplatform.service;

import com.example.educationplatform.module.*;

import java.util.List;

public interface EducationPlatformService {
    List<Courses> getStudentCourses(Long studentId);
    List<Courses> getTeacherCourses(Long teacherId);
    Students getStudentInformationById(Long id);
    Teachers getTeacherInformationById(Long id);
    List<Materials> getStudentMaterial(Long studentId);
    List<Materials> getTeacherMaterial(Long teacherId);
    List<Sessions> getStudentSessions(Long studentID);
}
