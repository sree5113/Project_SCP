package com.Smart.college_portal.controller;

import com.Smart.college_portal.dto.AdminDashBoardDTO;
import com.Smart.college_portal.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
@RequiredArgsConstructor
public class AdminDashboardController {

    @Autowired
    private final AdminDashboardService adminDashboardService;

    @GetMapping("/dashboard")
    public AdminDashBoardDTO getAdminDashboard() {
        return adminDashboardService.getDashboardData();
    }
}
