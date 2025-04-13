package com.Smart.college_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Long grade;
    private Long addmission_no;
    private String parent;
    private Long mobile;
    private LocalDate joinDate;
}

