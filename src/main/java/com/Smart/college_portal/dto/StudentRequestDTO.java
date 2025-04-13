package com.Smart.college_portal.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequestDTO {
    private String name;
    private String email;
    private String password;
    private Long grade;
    private Long addmission_no;
    private String parent;
    private Long mobile;
    private LocalDate joinDate;
}
