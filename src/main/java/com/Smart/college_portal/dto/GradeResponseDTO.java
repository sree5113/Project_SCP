package com.Smart.college_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradeResponseDTO {
    private Long id;
    private String subject;
    private String grade;
    private String studentName;
    private String facultyName;
}
