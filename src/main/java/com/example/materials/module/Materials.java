package com.example.materials.module;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Materials {
    private Long id;
    private Long courseId;
    private String name;
    private String content;
}
