package com.Smart.college_portal.controller;


import com.Smart.college_portal.dto.AttendanceRequestDTO;
import com.Smart.college_portal.dto.AttendanceResponseDTO;
import com.Smart.college_portal.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PreAuthorize("hasRole('FACULTY')")
    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> addAttendance(@RequestBody AttendanceRequestDTO dto){
        return ResponseEntity.ok(attendanceService.addAttendance(dto));
    }

    @PreAuthorize("hasRole('FACULTY')")
    @GetMapping("/student/{id}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendanceForStudent(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.getAttendanceForStudents(id));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/me")
    public ResponseEntity<List<AttendanceResponseDTO>> getMyAttendance() {
        return ResponseEntity.ok(attendanceService.getMyAttendance());
    }

}
