package com.Smart.college_portal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceRequestDTO {
    private Long studentId;
    private String subject;
    private boolean present;
    private LocalDate date;
}
