package com.Smart.college_portal.service;

import com.Smart.college_portal.dto.AttendanceRequestDTO;
import com.Smart.college_portal.dto.AttendanceResponseDTO;

import java.util.List;

public interface AttendanceService {

AttendanceResponseDTO addAttendance(AttendanceRequestDTO dto);
List<AttendanceResponseDTO> getAttendanceForStudents(Long studentId);
List<AttendanceResponseDTO> getMyAttendance();
}
