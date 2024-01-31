package com.springjwt.service;

import com.springjwt.entity.User;
import com.springjwt.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) {
        log.info("UserService::addUser: Adding user to the database: {}", user);
        User savedUser = this.userRepo.save(user);
        log.info("UserService::addUser: User added successfully: {}", savedUser);
        return savedUser;
    }

    public List<User> getAll() {
        log.info("UserService::getAll: Fetching all users from the database.");
        List<User> allUsers = this.userRepo.findAll();
        log.info("UserService::getAll: Fetched {} users.", allUsers.size());
        return allUsers;
    }

    public User getUserById(int userId) {
        log.info("UserService::getUserById: Fetching user by ID from the database: {}", userId);
        User user = this.userRepo.getById(userId);
        log.info("UserService::getUserById: Fetched user by ID: {}", user);
        return user;
    }

    public User deleteUserById(int userId) {
        log.info("UserService::deleteUserById: Deleting user by ID from the database: {}", userId);
        this.userRepo.deleteById(userId);
        log.info("UserService::deleteUserById: User deleted successfully.");
        return null;
    }

}
