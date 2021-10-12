package com.example.materials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MaterialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialsApplication.class, args);
    }

}
