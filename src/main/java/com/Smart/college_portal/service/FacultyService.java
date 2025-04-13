package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.FacultyRequestDTO;
import com.Smart.college_portal.dto.FacultyResponseDTO;

import java.util.List;

public interface FacultyService {
    FacultyResponseDTO createFaculty(FacultyRequestDTO dto);
    List<FacultyResponseDTO> getAllFaculty();
    FacultyResponseDTO getOwnProfile();
    FacultyResponseDTO updateOwnProfile(FacultyRequestDTO dto);
}
