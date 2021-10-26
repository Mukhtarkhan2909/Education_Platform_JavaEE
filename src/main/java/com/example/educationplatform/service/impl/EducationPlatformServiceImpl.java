package com.example.educationplatform.service.impl;

import com.example.educationplatform.module.*;
import com.example.educationplatform.service.EducationPlatformService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(
            fallbackMethod = "getCourseInformationByIdFallback",
            threadPoolKey = "getStudentCourses",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public List<Courses> getStudentCourses(Long studentId) {
        StudentCourses courses = restTemplate.getForObject("http://localhost:8085/courses/student-courses/" + studentId, StudentCourses.class);

        return courses.getStudentCourses();
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getCourseInformationByIdFallback",
            threadPoolKey = "getTeacherCourses",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public List<Courses> getTeacherCourses(Long teacherId) {
        TeacherCourses courses = restTemplate.getForObject("http://localhost:8085/courses/teacher-courses/" + teacherId, TeacherCourses.class);

        return courses.getTeacherCourses();
    }

    public Courses getCourseInformationByIdFallback(Long id) {
        Courses courses = new Courses();
        courses.setId(0L);
        courses.setName("Service Unavailable");
        return courses;
    }


    @Override
    @HystrixCommand(
            fallbackMethod = "getStudentInformationByIdFallback",
            threadPoolKey = "getStudentInformationById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public Students getStudentInformationById(Long id) {
        Students students = restTemplate.getForObject("http://localhost:8082/students/get-student/" + id, Students.class);

        return students;
    }

    public Students getStudentInformationByIdFallback(Long id) {
        Students students = new Students();
        students.setId(0L);
        students.setFullName("Service Unavailable");
        return students;
    }

    @HystrixCommand(
            fallbackMethod = "getTeacherInformationByIdFallback",
            threadPoolKey = "getTeacherInformationById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public Teachers getTeacherInformationById(Long id) {
        Teachers teachers = restTemplate.getForObject("http://localhost:8083/teachers/get-teacher/" + id, Teachers.class);

        return teachers;
    }

    public Teachers getTeacherInformationByIdFallback(Long id) {
        Teachers teachers = new Teachers();
        teachers.setId(0L);
        teachers.setFullName("Service Unavailable");
        return teachers;
    }

    @HystrixCommand(
            fallbackMethod = "getSessionInformationByIdFallback",
            threadPoolKey = "getStudentSessions",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public List<Sessions> getStudentSessions(Long studentID) {
        StudentSessions sessions = restTemplate.getForObject("http://localhost:8086/sessions/student-sessions/" + studentID, StudentSessions.class);

        return sessions.getStudentSessions();
    }

    public Sessions getSessionInformationByIdFallback(Long id) {
        Sessions sessions = new Sessions();
        sessions.setId(0L);
        sessions.setPassingDate("Service Unavailable");
        return sessions;
    }

    @HystrixCommand(
            fallbackMethod = "getMaterialInformationByIdFallback",
            threadPoolKey = "getStudentMaterial",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public List<Materials> getStudentMaterial(Long studentId) {
        StudentMaterials materials = restTemplate.getForObject("http://localhost:8084/materials/student-materials/" + studentId, StudentMaterials.class);

        return materials.getStudentMaterials();
    }

    @HystrixCommand(
            fallbackMethod = "getMaterialInformationByIdFallback",
            threadPoolKey = "getTeacherMaterial",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public List<Materials> getTeacherMaterial(Long teacherId) {
        TeacherMaterials materials = restTemplate.getForObject("http://localhost:8084/materials/teacher-materials/" + teacherId, TeacherMaterials.class);

        return materials.getTeacherMaterials();
    }

    public Materials getMaterialInformationByIdFallback(Long id) {
        Materials materials = new Materials();
        materials.setId(0L);
        materials.setName("Service Unavailable");
        return materials;
    }

}
