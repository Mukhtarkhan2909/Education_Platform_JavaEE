package com.example.studentinformation.service;

import com.example.studentinformation.module.User;

import java.util.List;
import java.util.Optional;

public interface UserInformationService {
    Optional<User> getUserInformationById(Long id);
    List<User> getAllUsers();
    Optional<User> getUserInformationByUsername(String username);
}
