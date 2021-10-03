package com.example.studentinformation.module;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Teachers {
    private Long id;
    private String fullName;
    private Degrees degree;
    private Integer course;
    private List<Courses> courses;
    private List<Sessions> sessions;
}
