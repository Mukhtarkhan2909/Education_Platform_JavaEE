package com.example.materialrequest.service;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.example.materialrequest.module.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class CourseInformationService {

    @Autowired
    private RestTemplate restTemplate;

//    @HystrixCommand(
//            fallbackMethod = "getUserBooksFallback",
//            threadPoolKey = "getUserBooks",
//            threadPoolProperties = {
//                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
//                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
//            }
//    )
    public Courses getBookById(String id) {

        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.getEncoder().encodeToString(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://course-information-service/courses/get-course/" + id,
                HttpMethod.GET, entity, Courses.class).getBody();
    }

    public Courses getCourseByIdFallback(Long id) {
        return new Courses(id, "Not available");
    }
}