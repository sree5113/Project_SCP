package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.AdminRegisterRequest;
import com.Smart.college_portal.entity.Admin;
import com.Smart.college_portal.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminRegisterService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerAdmin(AdminRegisterRequest request) {
        boolean exists = adminRepository.findByEmail(request.getEmail()).isPresent();
        if (exists) {
            return "Admin already exists with email: " + request.getEmail();
        }

        Admin admin = Admin.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        adminRepository.save(admin);
        return "Admin registered successfully!";
    }
}
