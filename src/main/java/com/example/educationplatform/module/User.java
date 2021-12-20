package com.example.educationplatform.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String userName;
    private String password;
    private String userType;
    private boolean isEnabled;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authorities> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
