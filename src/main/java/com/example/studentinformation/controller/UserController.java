package com.example.studentinformation.controller;

import com.example.studentinformation.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserInformationService userInformationService;

    @GetMapping("/get-users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userInformationService.getAllUsers());
    }

    @GetMapping("/find-user/{id}")
    public ResponseEntity<?> getUserInformationById(@PathVariable Long id) {
        return ResponseEntity.ok(userInformationService.getUserInformationById(id));
    }

    @GetMapping("/find-username/{username}")
    public ResponseEntity<?> getUserInformationById(@PathVariable String username) {
        return ResponseEntity.ok(userInformationService.getUserInformationByUsername(username));
    }

}
