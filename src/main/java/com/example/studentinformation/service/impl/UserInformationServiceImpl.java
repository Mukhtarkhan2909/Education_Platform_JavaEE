package com.example.studentinformation.service.impl;

import com.example.studentinformation.module.User;
import com.example.studentinformation.repository.UserRepository;
import com.example.studentinformation.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserInformationById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserInformationByUsername(String username) {
        return userRepository.findByUserName(username);
    }

}
