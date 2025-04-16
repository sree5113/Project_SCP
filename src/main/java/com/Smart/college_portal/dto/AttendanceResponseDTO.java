package com.Smart.college_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AttendanceResponseDTO {
    private Long id;
    private String studentName;
    private String subject;
    private boolean present;
    private LocalDate date;
}
