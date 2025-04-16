package com.Smart.college_portal.dto;

import lombok.Data;

@Data
public class GradeRequestDTO {
    private Long studentId;
    private String subject;
    private String grade;
}
