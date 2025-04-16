package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.GradeRequestDTO;
import com.Smart.college_portal.dto.GradeResponseDTO;

import java.util.List;

public interface GradeService {
    GradeResponseDTO addGrade(GradeRequestDTO dto);
    List<GradeResponseDTO> getGradesForStudent(Long studentId);
    List<GradeResponseDTO> getMyGrades();
}
