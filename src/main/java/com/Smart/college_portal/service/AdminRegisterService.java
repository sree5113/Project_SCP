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
        if (adminRepository.count() > 0) {
            return " Admin already exists. Only one admin allowed!";
        }

        Admin admin = Admin.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        adminRepository.save(admin);
        return " Admin registered successfully!";
    }

    public long countAdmins() {
        return adminRepository.count();
    }
}
