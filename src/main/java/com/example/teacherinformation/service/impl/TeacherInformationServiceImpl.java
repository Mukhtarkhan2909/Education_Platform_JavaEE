package com.example.teacherinformation.service.impl;

import com.example.teacherinformation.module.Teachers;
import com.example.teacherinformation.service.TeacherInformationService;
import org.springframework.stereotype.Service;

@Service
public class TeacherInformationServiceImpl implements TeacherInformationService {
    @Override
    public Teachers getTeacherInformationById(Long id) {
        return new Teachers(id, "Teacher1" , "Computer Science");
    }
}
