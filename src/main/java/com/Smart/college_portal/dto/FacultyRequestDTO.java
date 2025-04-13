package com.Smart.college_portal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacultyRequestDTO {
    private String name;
    private String email;
    private String password;
    private String department;
    private String subject;
    private String phone;
    private LocalDate joinDate;
}
