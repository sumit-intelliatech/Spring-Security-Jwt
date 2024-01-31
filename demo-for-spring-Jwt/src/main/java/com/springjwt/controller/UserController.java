package com.springjwt.controller;

import com.springjwt.entity.User;
import com.springjwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User addUser(@RequestBody User user) {
        log.info("Adding a new user: {}", user);
        User savedUser = this.userService.addUser(user);
        log.info("User added successfully: {}", savedUser);
        return savedUser;
    }

    @GetMapping("/get-All")
    public List<User> getAllUser() {
        log.info("Fetching all users");
        List<User> users = this.userService.getAll();
        log.info("Fetched {} users", users.size());
        return users;
    }

    @GetMapping("/get-By-Id/{id}")
    public User getUserById(@RequestParam("id") int userId) {
        log.info("Fetching user by ID: {}", userId);
        User user = this.userService.getUserById(userId);
        if (user != null) {
            log.info("User found: {}", user);
        } else {
            log.info("User not found for ID: {}", userId);
        }
        return user;
    }

    @DeleteMapping("delete-user-By-Id/{id}")
    public User deleteUserById(@RequestParam("id") int userId) {
        log.info("Deleting user by ID: {}", userId);
        User deletedUser = this.userService.deleteUserById(userId);
        if (deletedUser != null) {
            log.info("User deleted: {}", deletedUser);
        } else {
            log.info("User not found for deletion with ID: {}", userId);
        }
        return deletedUser;
    }
}
