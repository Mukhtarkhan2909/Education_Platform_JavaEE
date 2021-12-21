package com.example.courseinformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class CourseInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseInformationApplication.class, args);
    }

}
