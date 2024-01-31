package com.springjwt.configs;

import com.springjwt.dto.JwtRequestDto;
import com.springjwt.dto.JwtResponseDto;
import com.springjwt.entity.User;
import com.springjwt.repos.UserRepo;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtAuthService {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthController.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;
//    public ResponseEntity<?> generateToken(JwtRequestDto authenticationRequest) {
//        log.info("Received authentication request for username: {}", authenticationRequest.getEmail());
//
//        User userByUsername = userRepo.findUserByUsername(authenticationRequest.getEmail());
//
//        if (userByUsername != null && userByUsername.getPassword().equals(authenticationRequest.getEmail())) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//            String token = jwtUtil.generateAccessToken(userDetails);
//
//            String refreshToken = jwtUtil.generateRefreshToken(userDetails);
//
//            log.info("Authentication successful for username: {}", authenticationRequest.getEmail());
//
//            return ResponseEntity.ok(new JwtResponseDto(token, refreshToken));
//        } else {
//            log.warn("Authentication failed for username: {}", authenticationRequest.getEmail());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//        }
//
//    }


    public ResponseEntity<?> generateToken(JwtRequestDto authenticationRequest) {
        log.info("Received authentication request for username: {}", authenticationRequest.getUsername());

        User userByUsername = userRepo.findUserByUsername(authenticationRequest.getUsername());

        if (userByUsername != null && userByUsername.getPassword().equals(authenticationRequest.getUsername())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            String token = jwtUtil.generateAccessToken(userDetails);

            String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            log.info("Authentication successful for username: {}", authenticationRequest.getUsername());

            return ResponseEntity.ok(new JwtResponseDto(token, refreshToken));
        } else {
            log.warn("Authentication failed for username: {}", authenticationRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

    }

    public ResponseEntity<?> generateRefreshToken(String refreshToken) {

        try {
            final String username = jwtUtil.getUsernameFromToken(refreshToken);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateRefreshToken(refreshToken, userDetails)) {
                final String accessToken = jwtUtil.generateRefreshToken(userDetails);

                log.info("Token refresh successful for username: {}", username);

                return ResponseEntity.ok(new JwtResponseDto(accessToken, refreshToken));
            } else {
                log.warn("Invalid refresh token for username: {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }
        } catch (
                ExpiredJwtException e) {
            log.warn("Expired refresh token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired refresh token");
        }
    }
}
