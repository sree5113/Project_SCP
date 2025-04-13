package com.Smart.college_portal.controller;

import com.Smart.college_portal.entity.Admin;
import com.Smart.college_portal.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            return ResponseEntity.badRequest().body("Admin already exists");
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return ResponseEntity.ok("Admin registered successfully");
    }


}
