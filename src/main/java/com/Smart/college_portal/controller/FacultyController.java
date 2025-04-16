package com.Smart.college_portal.controller;

import com.Smart.college_portal.dto.FacultyRequestDTO;
import com.Smart.college_portal.dto.FacultyResponseDTO;
import com.Smart.college_portal.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FacultyResponseDTO> createFaculty(@RequestBody FacultyRequestDTO dto) {
        return ResponseEntity.ok(facultyService.createFaculty(dto));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<FacultyResponseDTO>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }


    @PreAuthorize("hasRole('FACULTY')")
    @GetMapping("/me")
    public ResponseEntity<FacultyResponseDTO> getOwnFacultyProfile() {
        return ResponseEntity.ok(facultyService.getOwnProfile());
    }


    @PreAuthorize("hasRole('FACULTY')")
    @PutMapping("/me")
    public ResponseEntity<FacultyResponseDTO> updateOwnFacultyProfile(@RequestBody FacultyRequestDTO dto) {
        return ResponseEntity.ok(facultyService.updateOwnProfile(dto));
    }



}
