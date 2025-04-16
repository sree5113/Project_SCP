package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.FacultyRequestDTO;
import com.Smart.college_portal.dto.FacultyResponseDTO;
import com.Smart.college_portal.dto.StudentRequestDTO;
import com.Smart.college_portal.dto.StudentResponseDTO;


import java.util.List;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO dto);
    List<StudentResponseDTO> getAllStudent();
    StudentResponseDTO getOwnProfile();
}
