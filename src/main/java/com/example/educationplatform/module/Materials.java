package com.example.educationplatform.module;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Materials {
    private Long id;
    private Courses course;
    private String name;
    private String content;
}
