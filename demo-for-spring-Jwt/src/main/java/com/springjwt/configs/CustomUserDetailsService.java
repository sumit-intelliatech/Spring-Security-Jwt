package com.springjwt.configs;

import com.springjwt.entity.User;
import com.springjwt.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService::loadUserByUsername: Loading user from DB for username: {}", username);

        User user = userRepo.findUserByUsername(username);

        if (user == null) {
            log.warn("CustomUserDetailsService::loadUserByUsername: User not found for username: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        log.info("CustomUserDetailsService::loadUserByUsername: User loaded successfully: {}", user);

        // Create and return a UserDetails object based on your User entity
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("CustomUserDetailsService::loadUserByUsername: Loading user from DB for username: {}", username);
//
//        User user = userRepo.findUserByUsername(username);
//
//        if (user == null) {
//            log.warn("CustomUserDetailsService::loadUserByUsername: User not found for username: {}", username);
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        log.info("CustomUserDetailsService::loadUserByUsername: User loaded successfully: {}", user);
//
//        // Create and return a UserDetails object based on your User entity
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
//    }
}
