package com.springjwt.configs;

import com.springjwt.dto.JwtRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class JwtAuthController {

    @Autowired
    private JwtAuthService jwtAuthService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) {
        return this.jwtAuthService.generateToken(authenticationRequest);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAuthenticationToken(@RequestParam String refreshToken) throws Exception {
        return this.jwtAuthService.generateRefreshToken(refreshToken);
    }
//    @PostMapping("/autheEmail")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) {
//        return this.jwtAuthService.generateToken(authenticationRequest);
//    }

}
