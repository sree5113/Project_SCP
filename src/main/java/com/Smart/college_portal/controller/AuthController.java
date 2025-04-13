// src/main/java/com/Smart/college_portal/controller/AuthController.java
package com.Smart.college_portal.controller;

import com.Smart.college_portal.dto.AdminRegisterRequest;
import com.Smart.college_portal.dto.LoginRequest;
import com.Smart.college_portal.dto.LoginResponse;
import com.Smart.college_portal.entity.Admin;
import com.Smart.college_portal.repository.AdminRepository;
import com.Smart.college_portal.security.JwtService;
import com.Smart.college_portal.service.AdminRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final AdminRegisterService adminRegisterService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegisterRequest request) {
        return ResponseEntity.ok(adminRegisterService.registerAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Cast to Admin (your custom entity)
        UserDetails user = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }


}
