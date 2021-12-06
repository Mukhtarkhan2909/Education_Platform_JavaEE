package com.example.studentinformation.service.impl;

import com.example.studentinformation.module.Degrees;
import com.example.studentinformation.module.Students;
import com.example.studentinformation.module.Teachers;
import com.example.studentinformation.service.StudentInformationService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentInformationServiceImpl implements StudentInformationService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getTeachersInformationByIdFallback",
            threadPoolKey = "getStudentInformationById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public Students getStudentInformationById(Long id) {
        Students student = new Students();
        student.setId(id);
        student.setFullName("Student1");
        student.setCourse(1);
        student.setDegree(Degrees.Bachelor);
        student.setCurator(restTemplate.getForObject("http://teacher-information-service/teacher/" + 1L, Teachers.class));
        return student;
    }

    public Students getTeachersInformationByIdFallback(Long id) {
        Students student = new Students();
        student.setId(0L);
        student.setFullName("Teachers Service Unavailable");
        return student;
    }

}
