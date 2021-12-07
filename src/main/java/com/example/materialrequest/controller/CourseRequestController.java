package com.example.materialrequest.controller;

import com.example.materialrequest.service.CourseInformationService;
import com.example.materialrequest.module.CourseRequest;
import com.example.materialrequest.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book/request")
public class CourseRequestController {

    private final Producer producer;
    private CourseInformationService courseInformationService;

    @Autowired
    public CourseRequestController(Producer producer, CourseInformationService courseInformationService) {
        this.producer = producer;
        this.courseInformationService = courseInformationService;
    }

    // TODO Ideally there should POST request
    @GetMapping
    public String sendMessageToKafkaTopic2(@RequestParam("userId") String userId,
                                           @RequestParam("courseId") String courseId) {

        CourseRequest courseRequest = new CourseRequest(userId, courseInformationService.getBookById(courseId));
        this.producer.bookRequestNotify(courseRequest);
        return "Your request sent successful!";
    }
}