package com.example.materials.module;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherMaterials {
    private Long teacherId;
    private List<Materials> teacherMaterials;

}
