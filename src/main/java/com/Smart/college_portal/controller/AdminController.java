
package com.Smart.college_portal.controller;

import com.Smart.college_portal.dto.AdminRegisterRequest;
import com.Smart.college_portal.repository.AdminRepository;
import com.Smart.college_portal.service.AdminRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminRegisterService adminRegisterService;
    private final AdminRepository adminRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegisterRequest request) {
        String result = adminRegisterService.registerAdmin(request);
        if (result.contains("already exists")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAdminAccount(Authentication authentication) {
        String email = authentication.getName();
        adminRepository.findByEmail(email).ifPresent(adminRepository::delete);
        return ResponseEntity.ok("Account deleted successfully");
    }
}