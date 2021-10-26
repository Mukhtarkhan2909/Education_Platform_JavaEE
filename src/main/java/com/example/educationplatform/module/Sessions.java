package com.example.educationplatform.module;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sessions {
    private Long id;
    private Courses course;
    private String passingDate;
    private Double grade;

}
