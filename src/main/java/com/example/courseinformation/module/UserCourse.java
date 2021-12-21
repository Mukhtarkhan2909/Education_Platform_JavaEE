package com.example.courseinformation.module;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "id", insertable = false, updatable = false)
    private Courses course;

}
