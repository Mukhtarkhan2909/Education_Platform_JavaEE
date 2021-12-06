package com.example.educationplatform.service.impl;

import com.example.educationplatform.module.*;
import com.example.educationplatform.service.EducationPlatformService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.util.ArrayList;
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
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public List<Courses> getStudentCourses(Long studentId) {

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        StudentCourses courses = restTemplate.exchange("http://course-information-service/courses/student-courses/" + studentId,
                HttpMethod.GET, entity, StudentCourses.class).getBody();
//        StudentCourses courses = restTemplate.getForObject("http://course-information-service/courses/student-courses/" + studentId, StudentCourses.class);

        return courses.getStudentCourses();
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getCourseInformationByIdFallback",
            threadPoolKey = "getTeacherCourses",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public List<Courses> getTeacherCourses(Long teacherId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        TeacherCourses courses = restTemplate.exchange("http://course-information-service/courses/teacher-courses/" + teacherId,
                HttpMethod.GET, entity, TeacherCourses.class).getBody();
//        TeacherCourses courses = restTemplate.getForObject("http://course-information-service/courses/teacher-courses/" + teacherId, TeacherCourses.class);

        return courses.getTeacherCourses();
    }

    public List<Courses> getCourseInformationByIdFallback(Long id) {
        List<Courses> coursess = new ArrayList<>();
        Courses courses = new Courses();
        courses.setId(id);
        courses.setName("Course Information Service Unavailable");
        coursess.add(courses);
        return coursess;
    }


    @Override
    @HystrixCommand(
            fallbackMethod = "getStudentInformationByIdFallback",
            threadPoolKey = "getStudentInformationById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public Students getStudentInformationById(Long id) {

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        return restTemplate.exchange("http://student-information-service/students/get-student/" + id,
                HttpMethod.GET, entity, Students.class).getBody();
//        return restTemplate.getForObject("http://student-information-service/students/get-student/" + id, Students.class);
    }

    public Students getStudentInformationByIdFallback(Long id) {
        Students students = new Students();
        students.setId(0L);
        students.setFullName("Student Information Service Unavailable");
        return students;
    }

    @HystrixCommand(
            fallbackMethod = "getTeacherInformationByIdFallback",
            threadPoolKey = "getTeacherInformationById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public Teachers getTeacherInformationById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        return restTemplate.exchange("http://teacher-information-service/teacher/" + id,
                HttpMethod.GET, entity, Teachers.class).getBody();
//        return restTemplate.getForObject("http://teacher-information-service/teacher/" + id, Teachers.class);
    }

    public Teachers getTeacherInformationByIdFallback(Long id) {
        Teachers teachers = new Teachers();
        teachers.setId(0L);
        teachers.setFullName("Teacher Information Service Unavailable");
        return teachers;
    }

    @HystrixCommand(
            fallbackMethod = "getSessionInformationByIdFallback",
            threadPoolKey = "getStudentSessions",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public List<Sessions> getStudentSessions(Long studentID) {

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        StudentSessions sessions = restTemplate.exchange("http://sessions-service/sessions/student-sessions/" + studentID,
                HttpMethod.GET, entity, StudentSessions.class).getBody();
//        StudentSessions sessions = restTemplate.getForObject("http://sessions-service/sessions/student-sessions/" + studentID, StudentSessions.class);

        return sessions.getStudentSessions();
    }

    public List<Sessions> getSessionInformationByIdFallback(Long id) {
        List<Sessions> sessions = new ArrayList<>();
        Sessions session = new Sessions();
        session.setId(0L);
        session.setPassingDate("Session Information Service Unavailable");
        sessions.add(session);
        return sessions;
    }

    @HystrixCommand(
            fallbackMethod = "getMaterialInformationByIdFallback",
            threadPoolKey = "getStudentMaterial",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public List<Materials> getStudentMaterial(Long studentId) {

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        StudentMaterials materials = restTemplate.exchange("http://materials-service/materials/student-materials/" + studentId,
                HttpMethod.GET, entity, StudentMaterials.class).getBody();
//        StudentMaterials materials = restTemplate.getForObject("http://materials-service/materials/student-materials/" + studentId, StudentMaterials.class);

        return materials.getStudentMaterials();
    }

    @HystrixCommand(
            fallbackMethod = "getMaterialInformationByIdFallback",
            threadPoolKey = "getTeacherMaterial",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public List<Materials> getTeacherMaterial(Long teacherId) {

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        TeacherMaterials materials = restTemplate.exchange("http://materials-service/materials/teacher-materials/" + teacherId,
                HttpMethod.GET, entity, TeacherMaterials.class).getBody();
//        TeacherMaterials materials = restTemplate.getForObject("http://materials-service/materials/teacher-materials/" + teacherId, TeacherMaterials.class);

        return materials.getTeacherMaterials();
    }

    public List<Materials> getMaterialInformationByIdFallback(Long id) {
        List<Materials> materials = new ArrayList<>();
        Materials material = new Materials();
        material.setId(0L);
        material.setName("Materials Service Unavailable");
        materials.add(material);
        return materials;
    }

}
