package com.example.educationplatform.module;

import lombok.*;

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
}
