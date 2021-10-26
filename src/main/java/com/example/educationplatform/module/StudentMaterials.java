package com.example.educationplatform.module;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentMaterials {
    private Long studentId;
    private List<Materials> studentMaterials;
}
