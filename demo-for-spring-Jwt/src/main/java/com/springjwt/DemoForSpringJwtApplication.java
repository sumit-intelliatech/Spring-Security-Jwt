package com.springjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoForSpringJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoForSpringJwtApplication.class, args);
    }

}
