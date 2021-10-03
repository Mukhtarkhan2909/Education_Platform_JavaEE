package com.example.studentinformation.module;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Courses {
    private Long id;
    private String courseName;
    private Double rating;

}
