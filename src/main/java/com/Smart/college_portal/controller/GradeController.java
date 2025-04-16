package com.Smart.college_portal.controller;

import com.Smart.college_portal.dto.GradeRequestDTO;
import com.Smart.college_portal.dto.GradeResponseDTO;
import com.Smart.college_portal.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PreAuthorize("hasRole('FACULTY')")
    @PostMapping
    public ResponseEntity<GradeResponseDTO> addGrade(@RequestBody GradeRequestDTO dto) {
        return ResponseEntity.ok(gradeService.addGrade(dto));
    }

    @PreAuthorize("hasRole('FACULTY')")
    @GetMapping("/student/{id}")
    public ResponseEntity<List<GradeResponseDTO>> getGradesForStudent(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.getGradesForStudent(id));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/me")
    public ResponseEntity<List<GradeResponseDTO>> getMyGrades() {
        return ResponseEntity.ok(gradeService.getMyGrades());
    }
}
