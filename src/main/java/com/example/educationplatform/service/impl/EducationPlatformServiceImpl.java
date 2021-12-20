package com.example.educationplatform.service.impl;

import com.example.educationplatform.module.*;
import com.example.educationplatform.repo.CourseRepository;
import com.example.educationplatform.repo.UserRepository;
import com.example.educationplatform.service.EducationPlatformService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationPlatformServiceImpl implements EducationPlatformService {

    @Autowired
    RestOperations restTemplate;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType, HttpEntity<String> entity) {
        return restTemplate.exchange(uri, HttpMethod.GET, entity, responseType).getBody();
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getUserInformationByIdFallback",
            threadPoolKey = "getUserInformationById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public User getUserInformationById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://user-information-service/users/find-user/" + id,
                HttpMethod.GET, entity, User.class).getBody();
    }

    public User getUserInformationByIdFallback(Long id) {
        User user = new User();
        user.setFullName("User-Information-Service is unavailable");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<User> users = this.exchangeAsList("http://user-information-service/users/get-users",
                new ParameterizedTypeReference<List<User>>() {}, entity);

        return users;
    }

    @Override
    public List<Courses> getUserCourses(Long userId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        List<UserCourse> userCourses = this.exchangeAsList("http://course-information-service/courses/user-courses/" + userId,
                new ParameterizedTypeReference<List<UserCourse>>() {}, entity);
        List<Courses> courses = new ArrayList<>();
        for (UserCourse u : userCourses) {
            Courses c = restTemplate.exchange("http://course-information-service/courses/find-course/" + u.getCourse().getId(),
                    HttpMethod.GET, entity, Courses.class).getBody();
            courses.add(c);
        }

        return courses;
    }

    @Override
    public List<Material> getAllMaterials() {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.exchangeAsList("http://materials-service/materials/get-materials",
                new ParameterizedTypeReference<List<Material>>() {}, entity);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getMaterialByIdFallback",
            threadPoolKey = "getMaterialById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public Material getMaterialById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://materials-service/materials/find-material/" + id,
                HttpMethod.GET, entity, Material.class).getBody();
    }

    public Material getMaterialByIdFallback(Long id) {
        Material material = new Material();
        material.setName("Material-Service is unavailable");
        return material;
    }

    @Override
    public List<Material> getUserMaterials(Long userId, Long courseId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.exchangeAsList("http://materials-service/materials/user-materials/" + userId + "/" + courseId,
                new ParameterizedTypeReference<List<Material>>() {}, entity);
    }

    @Override
    public List<Task> getAllTasks() {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.exchangeAsList("http://materials-service/materials/get-tasks",
                new ParameterizedTypeReference<List<Task>>() {}, entity);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getTaskByIdFallback",
            threadPoolKey = "getTaskById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public Task getTaskById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://materials-service/materials/find-task/" + id,
                HttpMethod.GET, entity, Task.class).getBody();
    }

    public Task getTaskByIdFallback(Long id) {
        Task task = new Task();
        task.setName("Material-Service is unavailable");
        return task;
    }

    @Override
    public List<Task> getUserTasks(Long userId, Long courseId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.exchangeAsList("http://materials-service/materials/user-tasks/" + userId + "/" + courseId,
                new ParameterizedTypeReference<List<Task>>() {}, entity);
    }

    @Override
    public List<Answer> getAllAnswers() {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.exchangeAsList("http://answers-service/answers/get-answers",
                new ParameterizedTypeReference<List<Answer>>() {}, entity);
    }

    @Override
    public Answer getAnswerById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://answers-service/answers/find-answer/" + id,
                HttpMethod.GET, entity, Answer.class).getBody();
    }

    @Override
    public Answer getUserAnswers(Long userId, Long taskId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://answers-service/answers/user-answers/"  + userId + "/" + taskId,
                HttpMethod.GET, entity, Answer.class).getBody();
    }

    @Override
    public List<Answer> getTaskAnswers(Long taskId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.exchangeAsList("http://answers-service/answers/task-answers/" + taskId,
                new ParameterizedTypeReference<List<Answer>>() {}, entity);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getUserInformationByIdFallback",
            threadPoolKey = "getUserInformationByUsername",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public User getUserInformationByUsername(String username) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://user-information-service/users/find-username/" + username,
                HttpMethod.GET, entity, User.class).getBody();
    }

    public User getUserInformationByIdFallback(String name) {
        User user = new User();
        user.setFullName("User-Information-Service is unavailable");
        return user;
    }

    @Override
    public List<Material> getCourseMaterials(Long courseId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.exchangeAsList("http://materials-service/materials/course-materials/" + courseId,
                new ParameterizedTypeReference<List<Material>>() {}, entity);
    }

    @Override
    public List<Task> getCourseTasks(Long courseId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return this.exchangeAsList("http://materials-service/materials/course-tasks/" + courseId,
                new ParameterizedTypeReference<List<Task>>() {}, entity);
    }

    @Override
    public void saveCourse(Courses course) {
        courseRepository.save(course);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getCourseInformationFallback",
            threadPoolKey = "getCourseInformation",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
//                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
//                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public Courses getCourseInformation(Long courseId) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://course-information-service/courses/find-course/" + courseId,
                HttpMethod.GET, entity, Courses.class).getBody();
    }

    public Courses getCourseInformationFallback(Long id) {
        Courses courses = new Courses();
        courses.setName("Course-Information-Service is unavailable");
        return courses;
    }

    @Override
    public List<Courses> getAllCourses() {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<Courses> courses = this.exchangeAsList("http://course-information-service/courses/get-courses",
                new ParameterizedTypeReference<List<Courses>>() {}, entity);

        return courses;
    }

}
