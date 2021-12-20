package com.example.educationplatform.controller;

import com.example.educationplatform.module.Courses;
import com.example.educationplatform.module.User;
import com.example.educationplatform.response.CourseResponse;
import com.example.educationplatform.response.ServerResponse;
import com.example.educationplatform.response.UserResponse;
import com.example.educationplatform.security.JwtUtil;
import com.example.educationplatform.security.MyUserDetailService;
import com.example.educationplatform.service.EducationPlatformService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/edu")
public class EducationPlatformController {
    @Autowired
    EducationPlatformService educationPlatformService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/auth")
    public ResponseEntity<ServerResponse> createAuthToken(@RequestBody HashMap<String, String> credential) {

        final String username = credential.get("userName");
        final String password = credential.get("password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final UserDetails userDetails = userDetailService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);

        ServerResponse resp = new ServerResponse();
        resp.setStatus("200");
        resp.setMessage("SUCCESS");
        resp.setAuthToken(jwt);
        resp.setUserId(userDetailService.getUserId(username));

        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            resp.setUserType("ADMIN");
        } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"))) {
            resp.setUserType("TEACHER");
        } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
            resp.setUserType("STUDENT");
        }

        return new ResponseEntity<ServerResponse>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    //***** User-Service *****//

    @GetMapping("/find-user/{id}")
    public ResponseEntity<?> getUserInformationById(@PathVariable Long id) {
        return ResponseEntity.ok(educationPlatformService.getUserInformationById(id));
    }
    @GetMapping("/get-users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(educationPlatformService.getAllUsers());
    }
    @GetMapping("/find-username/{username}")
    public ResponseEntity<?> getUserInformationByUsername(@PathVariable String username) {
        return ResponseEntity.ok(educationPlatformService.getUserInformationByUsername(username));
    }
    @PostMapping("/add-user")
    public ResponseEntity<UserResponse> addUser(
            @RequestParam(name = "fullName") String fullName,
            @RequestParam(name = "userName") String userName,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "userType") String userType) throws IOException {
        UserResponse resp = new UserResponse();
        User user = new User();
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setUserType(userType);
        user.setEnabled(true);
        educationPlatformService.saveUser(user);
        resp.setStatus("200");
        resp.setMessage("ADD_USER");
        resp.setOblist(educationPlatformService.getAllUsers());
        return new ResponseEntity<UserResponse>(resp, HttpStatus.OK);
    }
    @GetMapping("/delete-user")
    public ResponseEntity<UserResponse> deleteUser(@RequestParam(name = "userid") String userId) {
        UserResponse resp = new UserResponse();
        educationPlatformService.deleteUser(Long.parseLong(userId));
        resp.setStatus("200");
        resp.setMessage("DEL_USER");
        resp.setOblist(educationPlatformService.getAllUsers());
        return new ResponseEntity<UserResponse>(resp, HttpStatus.OK);
    }

    //***** Course-Service *****//

    @GetMapping("/find-course/{id}")
    public ResponseEntity<?> getCourseInformation(@PathVariable Long id) {
        return ResponseEntity.ok(educationPlatformService.getCourseInformation(id));
    }
    @GetMapping("/get-courses")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(educationPlatformService.getAllCourses());
    }
    @GetMapping("/get-user-courses/{userId}")
    public ResponseEntity<?> getUserCourses(@PathVariable Long userId) {
        return ResponseEntity.ok(educationPlatformService.getUserCourses(userId));
    }
    @PostMapping("/add-course")
    public ResponseEntity<CourseResponse> addCourse(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "numOfWeeks") String numOfWeeks) throws IOException {
        CourseResponse resp = new CourseResponse();
        Courses course = new Courses();
        course.setName(name);
        course.setNumOfWeeks(Integer.parseInt(numOfWeeks));
        educationPlatformService.saveCourse(course);
        resp.setStatus("200");
        resp.setMessage("ADD_COURSE");
        resp.setOblist(educationPlatformService.getAllCourses());
        return new ResponseEntity<CourseResponse>(resp, HttpStatus.OK);
    }
    @GetMapping("/delete-course")
    public ResponseEntity<CourseResponse> deleteCourse(@RequestParam(name = "courseid") String courseId) {
        CourseResponse resp = new CourseResponse();
        educationPlatformService.deleteCourse(Long.parseLong(courseId));
        resp.setStatus("200");
        resp.setMessage("DEL_COURSE");
        resp.setOblist(educationPlatformService.getAllCourses());
        return new ResponseEntity<CourseResponse>(resp, HttpStatus.OK);
    }
    //***** Material-Service *****//

    @GetMapping("/get-materials")
    public ResponseEntity<?> getAllMaterials() {
        return ResponseEntity.ok(educationPlatformService.getAllMaterials());
    }
    @GetMapping("/find-material/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable Long id) {
        return ResponseEntity.ok(educationPlatformService.getMaterialById(id));
    }
    @GetMapping("/get-user-materials/{userId}/{courseId}")
    public ResponseEntity<?> getUserMaterials(@PathVariable Long userId, @PathVariable Long courseId) {
        return ResponseEntity.ok(educationPlatformService.getUserMaterials(userId, courseId));
    }
    @GetMapping("/get-course-materials/{courseId}")
    public ResponseEntity<?> getCourseMaterials(@PathVariable Long courseId) {
        return ResponseEntity.ok(educationPlatformService.getCourseMaterials(courseId));
    }
    @GetMapping("/get-tasks")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(educationPlatformService.getAllTasks());
    }
    @GetMapping("/find-task/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(educationPlatformService.getTaskById(id));
    }
    @GetMapping("/get-user-tasks/{userId}/{courseId}")
    public ResponseEntity<?> getUserTasks(@PathVariable Long userId, @PathVariable Long courseId) {
        return ResponseEntity.ok(educationPlatformService.getUserTasks(userId, courseId));
    }
    @GetMapping("/get-course-tasks/{courseId}")
    public ResponseEntity<?> getCourseTasks(@PathVariable Long courseId) {
        return ResponseEntity.ok(educationPlatformService.getCourseTasks(courseId));
    }

    //***** Answers-Service *****//

    @GetMapping("/get-answers")
    public ResponseEntity<?> getAllAnswers() {
        return ResponseEntity.ok(educationPlatformService.getAllAnswers());
    }
    @GetMapping("/find-answer/{id}")
    public ResponseEntity<?> getAnswerById(@PathVariable Long id) {
        return ResponseEntity.ok(educationPlatformService.getAnswerById(id));
    }
    @GetMapping("/get-user-answers/{userId}/{taskId}")
    public ResponseEntity<?> getUserAnswers(@PathVariable Long userId, @PathVariable Long taskId) {
        return ResponseEntity.ok(educationPlatformService.getUserAnswers(userId, taskId));
    }
    @GetMapping("/get-task-answers/{taskId}")
    public ResponseEntity<?> getTaskAnswers(@PathVariable Long taskId) {
        return ResponseEntity.ok(educationPlatformService.getTaskAnswers(taskId));
    }

}
