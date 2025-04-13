package com.Smart.college_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FacultyResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String department;
    private String subject;
    private String phone;
    private LocalDate joinDate;
}
