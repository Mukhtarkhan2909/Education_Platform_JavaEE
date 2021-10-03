package com.example.studentinformation.module;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Students {
    private Long id;
    private String fullName;
    private Teachers curator;
    private Degrees degree;
    private Integer course;
    private List<Courses> courses;
    private List<Sessions> sessions;
}
