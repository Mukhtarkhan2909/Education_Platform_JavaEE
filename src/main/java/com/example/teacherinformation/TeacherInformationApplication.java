package com.example.teacherinformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TeacherInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherInformationApplication.class, args);
    }

}
