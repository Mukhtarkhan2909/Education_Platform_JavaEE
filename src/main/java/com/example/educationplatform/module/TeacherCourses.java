package com.example.educationplatform.module;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherCourses {
    private Long teacherId;
    private List<Courses> teacherCourses;
}
