package com.example.educationplatform.security;

import com.example.educationplatform.module.User;
import com.example.educationplatform.service.EducationPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    EducationPlatformService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<User> user = Optional.ofNullable(userService.getUserInformationByUsername(s));

        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user.map(MyUserDetails::new).get();
    }

    public Long getUserId(String s) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userService.getUserInformationByUsername(s));
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user.get().getId();
    }

}
