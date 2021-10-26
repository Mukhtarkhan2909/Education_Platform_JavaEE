package com.example.educationplatform.module;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCourses {
    private Long studentId;
    private List<Courses> studentCourses;

}
