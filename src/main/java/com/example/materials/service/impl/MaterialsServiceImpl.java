package com.example.materials.service.impl;

import com.example.materials.module.Courses;
import com.example.materials.module.Materials;
import com.example.materials.module.StudentMaterials;
import com.example.materials.module.TeacherMaterials;
import com.example.materials.service.MaterialsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class MaterialsServiceImpl implements MaterialsService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(
            fallbackMethod = "getStudentMaterialByIdFallback",
            threadPoolKey = "getStudentMaterialById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public StudentMaterials getStudentMaterialById(Long studentId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Materials materials = new Materials();
        materials.setId(1L);
        materials.setCourse(restTemplate.exchange("http://course-information-service/courses/get-course/" + 1,
                HttpMethod.GET, entity, Courses.class).getBody());
        materials.setName("Project");
        return new StudentMaterials(studentId, Collections.singletonList(materials));
    }

    public StudentMaterials getStudentMaterialByIdFallback(Long studentId) {
        Materials materials = new Materials();
        materials.setId(1L);
        materials.setName("Course Information Service Unavailable");
        return new StudentMaterials(studentId, Collections.singletonList(materials));
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getTeacherMaterialByIdFallback",
            threadPoolKey = "getTeacherMaterialById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public TeacherMaterials getTeacherMaterialById(Long teacherId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Materials materials = new Materials();
        materials.setId(1L);
        materials.setCourse(restTemplate.exchange("http://course-information-service/courses/get-course/" + 1,
                HttpMethod.GET, entity, Courses.class).getBody());
        materials.setName("Project");
        return new TeacherMaterials(teacherId, Collections.singletonList(materials));
    }

    @Override
    public Materials getMaterialById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Materials materials = new Materials();
        materials.setId(1L);
//        materials.setCourse(restTemplate.exchange("http://course-information-service/courses/get-course/" + 1,
//                HttpMethod.GET, entity, Courses.class).getBody());
        materials.setName("Project");
        return materials;
    }

    public TeacherMaterials getTeacherMaterialByIdFallback(Long studentId) {
        Materials materials = new Materials();
        materials.setId(1L);
        materials.setName("Course Information Service Unavailable");
        return new TeacherMaterials(studentId, Collections.singletonList(materials));
    }
}