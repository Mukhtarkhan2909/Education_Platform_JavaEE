package com.example.educationplatform.service;

import com.example.educationplatform.module.*;

import java.util.List;
import java.util.Optional;

public interface EducationPlatformService {
    List<User> getAllUsers();
    User getUserInformationById(Long id);
    List<Courses> getAllCourses();
    Courses getCourseInformation(Long courseId);
    List<Courses> getUserCourses(Long userId);
    List<Material> getAllMaterials();
    Material getMaterialById(Long id);
    List<Material> getUserMaterials(Long userId, Long courseId);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    List<Task> getUserTasks(Long userId, Long courseId);
    List<Answer> getAllAnswers();
    Answer getAnswerById(Long id);
    Answer getUserAnswers(Long userId, Long taskId);
    List<Answer> getTaskAnswers(Long taskId);
    User getUserInformationByUsername(String username);
    List<Material> getCourseMaterials(Long courseId);
    List<Task> getCourseTasks(Long courseId);
    void saveCourse(Courses course);
    void saveUser(User user);
    void deleteUser(Long userId);
    void deleteCourse(Long courseId);
}
