package com.example.educationplatform.service.impl;

import com.example.educationplatform.module.*;
import com.example.educationplatform.service.EducationPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationPlatformServiceImpl implements EducationPlatformService {

    @Autowired
    RestOperations restTemplate;

    @Override
    public List<Courses> getStudentCourses(Long studentId) {
        StudentCourses courses = restTemplate.getForObject("http://localhost:8085/courses/student-courses/" + studentId, StudentCourses.class);

        return courses.getStudentCourses();
    }

    @Override
    public List<Courses> getTeacherCourses(Long teacherId) {
        TeacherCourses courses = restTemplate.getForObject("http://localhost:8085/courses/teacher-courses/" + teacherId, TeacherCourses.class);

        return courses.getTeacherCourses();
    }

    @Override
    public Students getStudentInformationById(Long id) {
        Students students = restTemplate.getForObject("http://localhost:8082/students/get-student/" + id, Students.class);

        return students;
    }

    @Override
    public Teachers getTeacherInformationById(Long id) {
        Teachers teachers = restTemplate.getForObject("http://localhost:8083/teachers/get-teacher/" + id, Teachers.class);

        return teachers;
    }

    @Override
    public List<Sessions> getStudentSessions(Long studentID) {
        StudentSessions sessions = restTemplate.getForObject("http://localhost:8086/sessions/student-sessions/" + studentID, StudentSessions.class);

        return sessions.getStudentSessions();
    }

    @Override
    public List<Materials> getStudentMaterial(Long studentId) {
        StudentMaterials materials = restTemplate.getForObject("http://localhost:8084/materials/student-materials/" + studentId, StudentMaterials.class);

        return materials.getStudentMaterials();
    }

    @Override
    public List<Materials> getTeacherMaterial(Long teacherId) {
        TeacherMaterials materials = restTemplate.getForObject("http://localhost:8084/materials/teacher-materials/" + teacherId, TeacherMaterials.class);

        return materials.getTeacherMaterials();
    }
}
