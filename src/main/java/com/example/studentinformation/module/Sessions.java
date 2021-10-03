package com.example.studentinformation.module;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Sessions {
    private Long id;
    private Students student;
    private Courses course;
    private Teachers teacher;
    private Long grade;
}
