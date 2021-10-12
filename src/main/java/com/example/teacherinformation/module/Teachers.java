package com.example.teacherinformation.module;

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
    private String academicDegree;

}